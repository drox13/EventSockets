package runner;

import java.io.IOException;

import models.net.Server;

public class RunServer {

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}