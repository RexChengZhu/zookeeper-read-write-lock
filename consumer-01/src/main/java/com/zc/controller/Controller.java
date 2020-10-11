package com.zc.controller;

import com.zc.lock.ReadWriteLock;
import com.zc.lock.ZkLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {


    @Autowired
    private RestTemplate restTemplate;


    /**
     * ---------------非公平锁，重制命令每次执行都会优先于读取命令
     */
    @GetMapping("/test03")
    public void test03(){

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 10; i1++) {
                    try (ZkLock lock = new ReadWriteLock(ReadWriteLock.READ)){
                        Thread.sleep(50);
                        restTemplate.getForObject("http://PUBLIC/add", String.class);

                    }catch (Exception e){

                    }
                }
            }).start();
        }
    }


    @GetMapping("/test04")
    public void test04(){
        try (ZkLock lock = new ReadWriteLock(ReadWriteLock.WRITE)){
            restTemplate.getForObject("http://PUBLIC/clean", String.class);
        }catch (Exception e){

        }

    }


    @GetMapping("/test05")
    public void test05(){
        try (ZkLock lock = new ReadWriteLock(ReadWriteLock.READ)){
            restTemplate.getForObject("http://PUBLIC/clean", String.class);
        }catch (Exception e){

        }

    }
}
