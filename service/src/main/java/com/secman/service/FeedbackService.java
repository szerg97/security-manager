package com.secman.service;

import com.secman.model.Customer;
import com.secman.model.Feedback;
import com.secman.repository.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public List<Feedback> getAll(){
        return this.feedbackRepository.findAll();
    }

    public List<Feedback> getByCustomer(Customer customer){
        return this.feedbackRepository.findByCustomer(customer);
    }

    public Feedback getOne(Long id){
        Optional<Feedback> optional = this.feedbackRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Feedback addOne(Feedback feedback){
        this.feedbackRepository.save(feedback);
        return feedback;
    }

    public Feedback deleteOneById(Long id){
        Feedback feedback = getOne(id);
        if (feedback != null){
            this.feedbackRepository.delete(feedback);
            return feedback;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Feedback updateOne(Feedback pFeedback){
        Feedback feedback = getOne(pFeedback.getId());
        if (feedback != null) {
            feedback.copyFrom(pFeedback);
            this.feedbackRepository.save(feedback);
            return feedback;
        }
        throw new RuntimeException("Could not update");
    }
}
