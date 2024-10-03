package com.example.test1.service;

import com.example.test1.dto.ReturnDTO;
import com.example.test1.model.Returns;
import com.example.test1.repository.ReturnsRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReturnsService {
    private final ReturnsRepository returnsRepository;


    public ReturnsService(ReturnsRepository returnsRepository) {
        this.returnsRepository = returnsRepository;
    }

    public void create (Returns returns){
        Returns returns1 = new Returns();
        returns1.setReturnDate(returns.getReturnDate());
        returns1.setCommission(returns.getCommission());
        returns1.setCompanyName(returns.getCompanyName());
        returns1.setPaymentAmount(returns.getPaymentAmount());
        returns1.setTotalPayment(returns.getPaymentAmount() - returns.getCommission());
        returnsRepository.save(returns1);

    }

    public List<Returns> getAll(){
        return returnsRepository.findAll();
    }

    public List<ReturnDTO> getByDateAndCompany(LocalDate startDate, LocalDate endDate, String companyName){
            return returnsRepository.getByDateAndCompany(startDate,endDate, companyName);
    }
    public void delete(Long id){
        returnsRepository.deleteById(id);
    }

    public byte[] getExcelFIle(){
        return null;
    }
}
