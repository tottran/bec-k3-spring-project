package com.example.demo.service;

import com.example.demo.entity.Employer;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {
    public EmployerServiceImpl() {
        if(items.isEmpty()) {
            this.createDummyItems();
        }
    }

    @Override
    public void createDummyItems() {
        int id;
        for (int i = 0; i < 20; i++) {
            id = i;
            items.add(new Employer(id, "test" + id, "test", "test", "test"));
        }
    }

    @Override
    public List<Employer> getItems(Integer limit, Integer skip) {
        int page = skip / limit + 1;
        int totalElements = items.size();
        int remain = totalElements % limit;
        boolean isLastPage = page == (totalElements / limit + remain);
        int maxIndex = (page * (remain > 0 ? (limit - 1) : limit)) + remain;
        return
                skip > items.size() - 1 ? new ArrayList<>() :
                        items.subList(
                                skip,
                                Math.min(
                                        skip + (isLastPage ? ((limit - 1) + remain): limit),
                                        maxIndex)
                        );
    }

    @Override
    public Employer getItemById(Integer id) {
        return items.get(id);
    }

    @Override
    public Employer addItem(Employer item) {
        Employer p = new Employer(item.getId(), item.getName(), item.getEmail(), item.getProvince(), item.getDescription());
        items.add(p);
        return p;
    }

    @Override
    public Employer updateItemById(Integer id, Employer updateItem) {
        items.set(id, updateItem);
        return updateItem;
    }

    @Override
    public void deleteItemById(Integer id) {
        items.remove(id.intValue());
    }

    @Override
    public Long getItemsSize() {
        return (long) items.size();
    }
}
