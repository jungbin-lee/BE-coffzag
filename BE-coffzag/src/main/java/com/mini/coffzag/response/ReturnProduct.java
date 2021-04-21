package com.mini.coffzag.response;

import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ReturnProduct {
    private boolean ok;
    private List<Product> products = new ArrayList<>();
    private Page<Product> product;
//    {
//        @Override
//        public int getTotalPages() {
//            return 0;
//        }
//
//        @Override
//        public long getTotalElements() {
//            return 0;
//        }
//
//        @Override
//        public <U> Page<U> map(Function<? super Product, ? extends U> converter) {
//            return null;
//        }
//
//        @Override
//        public int getNumber() {
//            return 0;
//        }
//
//        @Override
//        public int getSize() {
//            return 0;
//        }
//
//        @Override
//        public int getNumberOfElements() {
//            return 0;
//        }
//
//        @Override
//        public List<Product> getContent() {
//            return null;
//        }
//
//        @Override
//        public boolean hasContent() {
//            return false;
//        }
//
//        @Override
//        public Sort getSort() {
//            return null;
//        }
//
//        @Override
//        public boolean isFirst() {
//            return false;
//        }
//
//        @Override
//        public boolean isLast() {
//            return false;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return false;
//        }
//
//        @Override
//        public boolean hasPrevious() {
//            return false;
//        }
//
//        @Override
//        public Pageable nextPageable() {
//            return null;
//        }
//
//        @Override
//        public Pageable previousPageable() {
//            return null;
//        }
//
//        @Override
//        public Iterator<Product> iterator() {
//            return null;
//        }
//    };
    private List<Review> reviews = new ArrayList<>();
}
