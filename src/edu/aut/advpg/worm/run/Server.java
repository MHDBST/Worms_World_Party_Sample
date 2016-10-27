package edu.aut.advpg.worm.run;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;

public class Server extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	ServerSocket server;
	Vector<WorkStation> vw = new Vector<WorkStation>();
	int number = 0;

	public Server() {
		setLayout(null);
		setResizable(false);
		Dimension d = this.getToolkit().getScreenSize();
		setSize(d.width, d.height);
		setLocation(d.width - getSize().width, d.height - getSize().height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Server");

		try {
			server = new ServerSocket(4444);
			startWaitingForClients();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		setVisible(true);
	}

	protected void startWaitingForClients() {
		new Thread(this).start();
	}

	public static void main(String[] args) {
		new Server();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	@Override
	public void run() {
		WorkStation works = null;
		Socket client = null;
		/* while (true) */
		for (int i = 0; i < 2; i++) {
			try {
				client = server.accept();
				works = new WorkStation(client, this, number++);
				vw.add(works);
				works.start();
				Thread.sleep(100);
			} catch (IOException e) {
				e.printStackTrace();
			}

			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setMessages(String message, int clientNumber) {
		for (int i = 0; i < vw.size(); i++) {
			if (vw.elementAt(i).getClientNumber() != clientNumber) 
			{
				vw.elementAt(i).sendMessage(message);//, clientNumber);
			}
		}
	}

}

class WorkStation extends Thread {
	Socket client;
	DataInputStream dis;
	DataOutputStream dos;
	private Server parent;
	int clientNumber;

	public WorkStation(Socket sc, Server parent, int clientNumber) {
		this.parent = parent;
		this.clientNumber = clientNumber;
		client = sc;
		try {
			dis = new DataInputStream(sc.getInputStream());
			dos = new DataOutputStream(sc.getOutputStream());
			dos.writeInt(clientNumber);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(this).start();
	}

	public void sendMessage(String messageBody){//, int clientNumber) {
		try {
			
			dos.write(messageBody.getBytes());
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		int num = 0;
		while (true) {
			try {
				/*num ++;
				if (num == 5){
					clientNumber++;
					num=0;
					clientNumber %= 2;
				}*/
				byte b[] = new byte[100000];
				dis.read(b);
				parent.setMessages((new String(b)).trim(), clientNumber);
				Thread.sleep(200);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getClientNumber() {
		return clientNumber;
	}
}
