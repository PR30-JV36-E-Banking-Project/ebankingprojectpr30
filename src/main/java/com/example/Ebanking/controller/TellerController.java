/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.controller;

import com.example.Ebanking.entities.TellerEntity;
import com.example.Ebanking.service.TellerServiceIF;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Hoang Duy Nhat
 */
@Controller
public class TellerController {
    @Autowired
    private TellerServiceIF tellerService;
    
    @GetMapping(value = "/list-teller")
    public String listTellerrs(HttpServletRequest request, Model theModel) {
	List<TellerEntity> tellers = tellerService.getTellers();
        PagedListHolder pagedListHolder = new PagedListHolder(tellers);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(5);
                
	theModel.addAttribute("pagedListHolder", pagedListHolder);
                
	return "tellerList";
    }
    
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
	TellerEntity theTeller = new TellerEntity();
	theModel.addAttribute("teller", theTeller);
	return "tellerForm";
    }
    
    @PostMapping("/saveTeller")
    @Transactional
    public String saveTeller(@ModelAttribute("teller") TellerEntity theTeller) {
	tellerService.saveTeller(theTeller);
	return "redirect:/list-teller";
    }

    @GetMapping("/updateTellerForm")
    public String showFormForUpdate(@RequestParam("tellerID") int theId,
	    Model theModel) {
	TellerEntity theTeller = tellerService.getTeller(theId);
	theModel.addAttribute("teller", theTeller);
	return "tellerForm";
    }

    @GetMapping("/deleteTeller")
    public String deleteTeller(@RequestParam("tellerID") int theId) {
	tellerService.deleteTeller(theId);
	return "redirect:/list-teller";
    }
}
