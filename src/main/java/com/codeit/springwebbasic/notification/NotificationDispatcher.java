package com.codeit.springwebbasic.notification;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationDispatcher {

    //알림 전부 발송하기
    //컬렉션으로 빈 주입 받기
    private final List<NotificationService> allServices;

    public NotificationDispatcher(List<NotificationService> allServices) {
        this.allServices = allServices;
        System.out.println("등록된 알림 서버스: " + allServices.size() + "개");
    }

    public void broadcast(String recipient, String message){
        allServices.forEach(service -> {
            service.sendNotification(recipient, message);
        });
    }
}
