/**
 * Z_UnifyService com.zens.unify.task.handler.LinuxSSh.java
 * 2014年11月13日 下午3:34:27
 * LinuxSSh
 */
package com.zens.unify.task.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.zens.unify.entity.CameraBase.SSH;
import com.zens.unify.utils.StringUtils;

/**
 * 
 * Z_UnifyService com.zens.unify.task.handler.LinuxSSh.java
 * LinuxSSh
 * 2014年11月13日 下午3:34:27
 * @author vector
 *
 */
public class LinuxSSh {
	Logger log = Logger.getLogger(getClass());
	
	private Session session;
	
	/**
	 * 
	 */
	public LinuxSSh(SSH ssh) {
		this(ssh.getHost(), ssh.getUser(), ssh.getPasswd(), ssh.getPort());
	}
	/**
	 * @param host
	 * @param user
	 * @param pass
	 * @param command
	 */
	public LinuxSSh(String host, String user, String pass) {
		this(host, user, pass, 22);
	}
	/**
	 * @param host
	 * @param user
	 * @param pass
	 * @param command
	 * @param port
	 */
	public LinuxSSh(String host, String user, String pass, int port) {
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(user, host, port);
			session.setPassword(pass);
			session.setTimeout(2000);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

		} catch (JSchException e) {
			log.info(e.getMessage());
		}
	}
	/**
	 * 执行远程命令
	 * @return
	 */
	public List<String> exec(String command) {
		List<String> fileNames = new ArrayList<String>();
		try {
			Channel channel = session.openChannel("exec");
			ChannelExec execChannel = (ChannelExec) channel;
			execChannel.setCommand(command);
			InputStream in = channel.getInputStream();
			BufferedReader br =  new BufferedReader(new InputStreamReader(in));
			
			channel.connect();

			String line;
			while ((line = br.readLine()) != null) {
				if(StringUtils.hasText(line))
					fileNames.add(line);
			}
			/*int c = -1;
			while ((c = in.read()) != -1) {
				sb.append((char) c);
			}*/

			channel.disconnect();
		} catch (JSchException | IOException e) {
			log.info(e.getMessage());
		}

		return fileNames;
	}
	/**
	 * 关闭
	 */
	public void close() {
		session.disconnect();
	}
	
	public static void main(String[] args) {
		LinuxSSh ssh = new LinuxSSh("192.168.0.200", "root", "zensvision88888", 22);
		List<String> fileNames = ssh.exec("ls /opt/");
		for(String fn : fileNames)
			System.out.println(fn);
		ssh.close();
	}
}
