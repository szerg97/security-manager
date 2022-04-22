package com.secman.service;

import com.secman.model.Security;
import com.secman.repository.SecurityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityService {

    private final SecurityRepository categoryRepository;

    public List<Security> getAll(){
        return this.categoryRepository.findAll();
    }

    public Security getOne(Long id){
        Optional<Security> optional = this.categoryRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Security addOne(Security category){
        this.categoryRepository.save(category);
        return category;
    }

    public Security deleteOneById(Long id){
        Security category = getOne(id);
        if (category != null){
            this.categoryRepository.delete(category);
            return category;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Security updateOne(Security pCategory){
        Security category = getOne(pCategory.getId());
        if (category != null) {
            category.copyFrom(pCategory);
            this.categoryRepository.save(category);
            return category;
        }
        throw new RuntimeException("Could not update");
    }
}
