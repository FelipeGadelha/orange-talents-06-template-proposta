package br.com.zupacademy.felipe.gadelha.proposta.api.v1.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.zupacademy.felipe.gadelha.proposta.api.builder.AuthorizedClient;
import br.com.zupacademy.felipe.gadelha.proposta.api.builder.TokenAuth;
import br.com.zupacademy.felipe.gadelha.proposta.api.convert.JacksonParse;
import br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.request.ProposalRq;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.Proposal;
import br.com.zupacademy.felipe.gadelha.proposta.domain.entity.StatusProposal;

@Transactional
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProposalControllerTest {

	final String BASE_PATH = "/v1/proposals";
	
	@Autowired
	private MockMvc mockMvc;
	@PersistenceContext
	private EntityManager manager;	
	@Autowired
	private JacksonParse jackson;
	@Autowired
	private AuthorizedClient builder;

	@BeforeEach
	void setUp() {
		manager.createQuery("delete from Proposal").executeUpdate();
		manager.flush();
	}
	
	@Test
	@DisplayName("should successfully save a proposal")
	void test() throws JsonProcessingException, Exception {
		System.err.println("---------------- TESTE" + builder.getToken(TokenAuth.READ_WRITE));
		var document = "71179002024";
		var proposalRq = new ProposalRq(document, "felipe@email.com", "Felipe Gadelha", "Rua 1", new BigDecimal(3000));
		mockMvc.perform(post(BASE_PATH)
				.header("Authorization", builder.getToken(TokenAuth.READ_WRITE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(jackson.toJson(proposalRq)))
		.andExpect(status().isCreated());
		
		var proposal = manager.createQuery("from Proposal p where p.document =:pDocument", Proposal.class)
				.setParameter("pDocument", proposalRq.getDocument())
				.getSingleResult();
		assertNotNull(proposal.getId());
		assertThat(proposalRq.getDocument()).isEqualTo(proposal.getDocument());
		assertThat(proposalRq.getEmail()).isEqualTo(proposal.getEmail());
		assertThat(proposalRq.getName()).isEqualTo(proposal.getName());
		assertThat(proposalRq.getAddress()).isEqualTo(proposal.getAddress());
		assertThat(proposalRq.getSalary()).isEqualTo(proposal.getSalary());
	}
	@Test
	@DisplayName("should return 400 for proposal null")
	void test1() throws JsonProcessingException, Exception {
		var proposalRq = new ProposalRq(null, null, null, null, null);
		mockMvc.perform(post(BASE_PATH)
				.header("Authorization", builder.getToken(TokenAuth.READ_WRITE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(jackson.toJson(proposalRq)))
		.andExpect(status().isBadRequest());
	}		
	@Test
	@DisplayName("should return 400 for proposal with document repeated")
	void test2() throws JsonProcessingException, Exception {
		var document = "71179002024";
		var proposalRq = new ProposalRq(document, "felipe@email.com", "Felipe Gadelha", "Rua 1", new BigDecimal(3000));
		mockMvc.perform(post(BASE_PATH)
				.header("Authorization", builder.getToken(TokenAuth.READ_WRITE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(jackson.toJson(proposalRq)))
		.andExpect(status().isCreated());
		var proposalRqRepeat = new ProposalRq(document, "felipe@test.com", "Felipe teste", "Rua 2", new BigDecimal(3000));
		mockMvc.perform(post(BASE_PATH)
				.header("Authorization", builder.getToken(TokenAuth.READ_WRITE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(jackson.toJson(proposalRqRepeat)))
		.andExpect(status().isUnprocessableEntity())
		.andExpect(jsonPath("$.details", is("422 UNPROCESSABLE_ENTITY Já existe uma proposta com esse documento")));
	}
	@Test
	@DisplayName("should successfully save a proposal with the status NOT_ELIGIBLE")
	void test3() throws JsonProcessingException, Exception {
		var document = "38116222007";
		var proposalRq = new ProposalRq(document, "felipe@email.com", "Felipe Gadelha", "Rua 1", new BigDecimal(3000));
		mockMvc.perform(post(BASE_PATH)
				.header("Authorization", builder.getToken(TokenAuth.READ_WRITE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(jackson.toJson(proposalRq)))
		.andExpect(status().isCreated());
		
		var proposal = manager.createQuery("from Proposal p where p.document =:pDocument", Proposal.class)
				.setParameter("pDocument", proposalRq.getDocument())
				.getSingleResult();
		assertNotNull(proposal.getId());
		assertThat(proposal.getDocument()).isEqualTo(proposalRq.getDocument());
		assertThat(proposal.getEmail()).isEqualTo(proposalRq.getEmail());
		assertThat(proposal.getName()).isEqualTo(proposalRq.getName());
		assertThat(proposal.getAddress()).isEqualTo(proposalRq.getAddress());
		assertThat(proposal.getSalary()).isEqualTo(proposalRq.getSalary());
		assertThat(proposal.getStatus()).isEqualTo(StatusProposal.NOT_ELIGIBLE);
	}
	
}
