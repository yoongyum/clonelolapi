package com.clonelol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClonelolapiApplicationTests {

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void contextLoads() throws JsonProcessingException {

		Json json = new Json();
		String s = objectMapper.writeValueAsString(json);

		System.out.println("s = " + s);
	}

	static class Json{
		private String id;
		private String password;

		@Override
		public String toString() {
			return "Json{" +
					"id='" + id + '\'' +
					", password='" + password + '\'' +
					'}';
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
