package com.su.hello.server;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "myHelloService")
// value:,propagation:传播行为,isolation:隔离级别，
@Transactional(value = "MyTransactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = {IOException.class,SQLException.class,Exception.class},readOnly = true)
public class HelloServer {
	
}
