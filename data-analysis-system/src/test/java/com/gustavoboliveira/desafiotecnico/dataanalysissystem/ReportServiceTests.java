package com.gustavoboliveira.desafiotecnico.dataanalysissystem;

import com.gustavoboliveira.desafiotecnico.dataanalysissystem.repositories.SystemRepository;
import com.gustavoboliveira.desafiotecnico.dataanalysissystem.services.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ReportServiceTests {

	@Autowired
	private ReportService reportService;
	@Autowired
	private SystemRepository repository;

	@BeforeEach
	void init(){
		repository.getSalesmanList().clear();
		repository.getCustomerList().clear();
		repository.getSaleList();
		String[] salesman1 = {"001", "12345678891234", "Diego", "50000"};
		String[] salesman2 = {"001", "3245678865434", "Renato", "40000.99"};
		String[] customer1 = {"002", "2345675434544345", "Joao da Silva", "Rural"};
		String[] customer2 = {"002", "2345675433444345", "Eduardo Pereira", "Rural"};
		String[] sale1 = {"003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Diego"};
		String[] sale2 = {"003", "08", "[1-34-10,2-33-1.50,3-40-0.10]", "Renato"};
		repository.buildSalesman(salesman1);
		repository.buildSalesman(salesman2);
		repository.buildCustomer(customer1);
		repository.buildCustomer(customer2);
		repository.buildSale(sale1);
		repository.buildSale(sale2);
	}

	@Test
	void shouldReturnNumberOfClientsSuccessTest() {
		if(reportService.amountClients() == 0){
			String[] customer1 = {"002", "2345675434544345", "Joao da Silva", "Rural"};
			String[] customer2 = {"002", "2345675433444345", "Eduardo Pereira", "Rural"};
			repository.buildCustomer(customer1);
			repository.buildCustomer(customer2);
		}
		assertEquals(2, reportService.amountClients());
	}

	@Test
	void shouldReturnZeroWhenNoClientsTest(){
		repository.getCustomerList().clear();
		assertEquals(0, reportService.amountClients());
	}

	@Test
	void shouldReturnNumberOfSalesmanSuccessTest(){
		if(reportService.amountSalesman() == 0){
			String[] salesman1 = {"001", "12345678891234", "Diego", "50000"};
			String[] salesman2 = {"001", "3245678865434", "Renato", "40000.99"};
			repository.buildSalesman(salesman1);
			repository.buildSalesman(salesman2);
		}
		assertEquals(2, reportService.amountSalesman());
	}

	@Test
	void shouldReturnZeroWhenNoSalesmanTest(){
		repository.getSalesmanList().clear();
		assertEquals(0, reportService.amountSalesman());
	}

	@Test
	void shouldReturnIdMostExpensiveSaleSuccessTest(){
		assertEquals(10, reportService.idMostSaleExpensive().get());
	}

	@Test
	void shouldReturnDefaultIdWhenNoSalesTest(){
		repository.getSaleList().clear();
		assertEquals(-1, reportService.idMostSaleExpensive().get());
	}

	@Test
	void shouldReturnWorstSalesmanSuccessTest(){
		assertTrue(reportService.worstSalesman().getName().equals("Renato"));
	}

	@Test
	void shouldReturnNullWhenNoSalesmanTest(){
		repository.getSalesmanList().clear();
		assertTrue(reportService.worstSalesman() == null);
	}
}
