package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/bidList")
public class BidListController {
    // Inject Bid service
    private final BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    @RequestMapping("/list")
    public String home(Model model) {
        // all service find all bids to show to the view
        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/add")
    public String addBidForm(BidList bid) {

        return "bidList/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // check data valid and save to db, after saving return bid list
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.save(bid);
        return "redirect:/bidList/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get Bid by Id and to model then show to the form
        BidList bidList = bidListService.findById(id);
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") Integer id,
                            @Valid BidList bidList,
                            BindingResult result,
                            Model model) {
        // check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            bidList.setBidListId(id);
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListService.save(bidList);
        return "redirect:/bidList/list";

    }

    @GetMapping("/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // Find Bid by Id and delete the bid, return to Bid list
        bidListService.deleteById(id);
        return "redirect:/bidList/list";
    }
}
