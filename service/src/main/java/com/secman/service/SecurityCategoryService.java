package com.secman.service;

import com.secman.model.SecurityCategory;
import com.secman.repository.SecurityCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityCategoryService {

    private final SecurityCategoryRepository categoryRepository;

    public List<SecurityCategory> getAll(){
        return this.categoryRepository.findAll();
    }

    public SecurityCategory getOne(Long id){
        Optional<SecurityCategory> optional = this.categoryRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public SecurityCategory addOne(SecurityCategory category){
        this.categoryRepository.save(category);
        return category;
    }

    public SecurityCategory deleteOneById(Long id){
        SecurityCategory category = getOne(id);
        if (category != null){
            this.categoryRepository.delete(category);
            return category;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public SecurityCategory updateOne(SecurityCategory pCategory){
        SecurityCategory category = getOne(pCategory.getId());
        if (category != null) {
            category.copyFrom(pCategory);
            this.categoryRepository.save(category);
            return category;
        }
        throw new RuntimeException("Could not update");
    }
}
