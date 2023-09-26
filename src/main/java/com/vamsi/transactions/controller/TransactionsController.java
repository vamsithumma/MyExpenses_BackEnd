package com.vamsi.transactions.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vamsi.transactions.dto.TransactionDTO;
import com.vamsi.transactions.entity.Transactions;
import com.vamsi.transactions.entity.User;
import com.vamsi.transactions.exception.ResourceNotFoundException;
import com.vamsi.transactions.repository.TransactionRepository;
import com.vamsi.transactions.repository.UserRepository;


@CrossOrigin
@RestController
@RequestMapping("/v1/transactions")
public class TransactionsController {
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	 @GetMapping
	    public ResponseEntity<List<Transactions>> getAllTransactions() {
	        List<Transactions> transactions = (List<Transactions>) transactionRepository.findAll();
	        if (transactions.isEmpty()) {
	            return new ResponseEntity<>(transactions,HttpStatus.OK);
	        }

	        return new ResponseEntity<>(transactions, HttpStatus.OK);
	    }
	    @GetMapping("/{id}")
		public ResponseEntity<Transactions> getTransactionById(@PathVariable(value = "id") String transactionId)
				throws ResourceNotFoundException {
			Transactions transaction =transactionRepository.findById(transactionId)
					.orElseThrow(() -> new ResourceNotFoundException("Transaction not found for this id :: " + transactionId));
			return ResponseEntity.ok().body(transaction);
		}
	@PostMapping
    public ResponseEntity<Transactions> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO)throws ResourceNotFoundException  {
		User user = userRepository.findById(transactionDTO.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + transactionDTO.getUserId()));
		System.out.println(transactionDTO.getUserId());
        Transactions transaction = new Transactions();
        transaction.setUser(user);
        transaction.setDate(transactionDTO.getDate());
        transaction.setRepayDate(null); // Set to null for the first insert
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setMode(transactionDTO.getMode());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setDebitCard(transactionDTO.getDebitCard());
        transaction.setCreditCard(transactionDTO.getCreditCard());
        transaction.setBorrowedFromMe(transactionDTO.getBorrowedFromMe());
        transaction.setBorrowedByMe(transactionDTO.getBorrowedByMe());
        transaction.setStatus(transactionDTO.getStatus());
        Transactions savedTransaction = transactionRepository.save(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }
	@PostMapping("/{id}")
	public ResponseEntity<Transactions> updateTransaction(@PathVariable(value = "id") String transactionId,
			@Valid @RequestBody TransactionDTO updatedTransaction)throws ResourceNotFoundException {
		Transactions existingTransaction = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction not found for this id :: " + transactionId));		
		existingTransaction.setDate(updatedTransaction.getDate());
        existingTransaction.setRepayDate(updatedTransaction.getRepayDate());
        existingTransaction.setDescription(updatedTransaction.getDescription());
        existingTransaction.setDebitCard(updatedTransaction.getDebitCard());
        existingTransaction.setCreditCard(updatedTransaction.getCreditCard());
        existingTransaction.setBorrowedFromMe(updatedTransaction.getBorrowedFromMe());
        existingTransaction.setBorrowedByMe(updatedTransaction.getBorrowedByMe());
        existingTransaction.setStatus(updatedTransaction.getStatus());
        existingTransaction.setAmount(updatedTransaction.getAmount());
        existingTransaction.setMode(updatedTransaction.getMode());
		final Transactions updatedTrans = transactionRepository.save(existingTransaction);
		return ResponseEntity.ok(updatedTrans);
	}
    @GetMapping("/delete/{id}")
	public Map<String, Boolean> deleteTransaction(@PathVariable(value = "id") String transactionId)
			throws ResourceNotFoundException {
    	Transactions transaction = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction not found for this id :: " + transactionId));		
		
		transactionRepository.delete(transaction);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
    @GetMapping("/{startdate}/{enddate}/{userId}")
    public ResponseEntity<List<Transactions>> getTransactionsByDates(@PathVariable(value = "startdate") String startdate,@PathVariable(value = "enddate") String enddate
    		, @PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
//    	User user = userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
    	
    	List<Transactions> transaction = transactionRepository.getTransactionsByDates(convertDateStr2Millis(startdate), convertDateStr2Millis(enddate), userId);
    	if(transaction.isEmpty()) {
    		return new ResponseEntity<>(transaction,HttpStatus.OK);
    	}
    	return new ResponseEntity<>(transaction, HttpStatus.OK);
    	
    }
    
    @GetMapping("/userid={userId}")
    public ResponseEntity<List<Transactions>> getTransactionsByUserId(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
//    	User user = userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
    	
    	List<Transactions> transaction = transactionRepository.getTransactionsByUserId( userId);
    	if(transaction.isEmpty()) {
    		return new ResponseEntity<>(transaction,HttpStatus.OK);
    	}
    	return new ResponseEntity<>(transaction, HttpStatus.OK);
    	
    }
    
    public long convertDateStr2Millis(String inputDate)throws ResourceNotFoundException {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	try{
			date = df.parse(inputDate);
			Long outputDate=date.getTime();
			System.out.println(outputDate);
			return outputDate;
    	}catch(ParseException e){
                e.printStackTrace();
        }
    	return 0;
    }
}

