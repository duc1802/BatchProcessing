package com.example.bookapiver2.service;

import com.example.bookapiver2.entity.Book;
import com.example.bookapiver2.entity.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BatchProcessingService {
    //Tùy biến JPA bằng cách sử dụng :
    //EntityManager: về nhà đọc thêm cái này, JpaRepository mặc đinh ko hỗ trợ
    //BatchProcessing nếu muốn sử dụng thì cần tùy biến trong application

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional //thực hiện một giao dịch kiểu ACID
    public void batchInsertBooks(){
        Publisher publisher1 = entityManager.find(Publisher.class,1L);
        Publisher publisher2 = entityManager.find(Publisher.class,2L);

        Set<Publisher> publishers = new HashSet<>();
        publishers.add(publisher1);
        publishers.add(publisher2);

        for(int i = 0;i < 1000000; i++){
            Book book = new Book();
            book.setName("Book" + i);
            book.setPublishers(publishers);
            entityManager.persist(book);
            if(i % 50 ==0){
                entityManager.flush();//release memory giải phóng mem
                entityManager.clear();
            }
        }


    }

}
