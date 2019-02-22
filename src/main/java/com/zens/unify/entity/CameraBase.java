/**
 * Z_UnifyService com.zens.unify.entity.CameraBase.java
 * 2014年11月1日 下午4:09:54
 * CameraBase
 */
package com.zens.unify.entity;

/**
 * 
 * Z_UnifyService com.zens.unify.entity.CameraBase.java
 * CameraBase
 * 2014年11月1日 下午4:09:54
 * @author vector
 *
 */
public class CameraBase {
	private String dirPrefix;
	
	private String dirSuffix;

	private String filePrefix;
	
	private String fileSuffix;
	
	private SSH ssh;

	/**
	 * @return the dirPrefix
	 */
	public String getDirPrefix() {
		return dirPrefix;
	}

	/**
	 * @param dirPrefix the dirPrefix to set
	 */
	public void setDirPrefix(String dirPrefix) {
		this.dirPrefix = dirPrefix;
	}

	/**
	 * @return the dirSuffix
	 */
	public String getDirSuffix() {
		return dirSuffix;
	}

	/**
	 * @param dirSuffix the dirSuffix to set
	 */
	public void setDirSuffix(String dirSuffix) {
		this.dirSuffix = dirSuffix;
	}

	/**
	 * @return the filePrefix
	 */
	public String getFilePrefix() {
		return filePrefix;
	}

	/**
	 * @param filePrefix the filePrefix to set
	 */
	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}

	/**
	 * @return the fileSuffix
	 */
	public String getFileSuffix() {
		return fileSuffix;
	}

	/**
	 * @param fileSuffix the fileSuffix to set
	 */
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
	
	/**
	 * @return the ssh
	 */
	public SSH getSsh() {
		return ssh;
	}

	/**
	 * @param ssh the ssh to set
	 */
	public void setSsh(SSH ssh) {
		this.ssh = ssh;
	}

	/**
	 * SSH
	 * CameraBase
	 * Z_UnifyService com.zens.unify.entity.CameraBase.java
	 * SSH
	 * 2014年11月13日 下午4:03:49
	 * @author vector
	 *
	 */
	public class SSH{
		private String host;
		private String user;
		private String passwd;
		private int port;
		/**
		 * @return the host
		 */
		public String getHost() {
			return host;
		}
		/**
		 * @param host the host to set
		 */
		public void setHost(String host) {
			this.host = host;
		}
		/**
		 * @return the user
		 */
		public String getUser() {
			return user;
		}
		/**
		 * @param user the user to set
		 */
		public void setUser(String user) {
			this.user = user;
		}
		/**
		 * @return the passwd
		 */
		public String getPasswd() {
			return passwd;
		}
		/**
		 * @param passwd the passwd to set
		 */
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		/**
		 * @return the port
		 */
		public int getPort() {
			return port;
		}
		/**
		 * @param port the port to set
		 */
		public void setPort(int port) {
			this.port = port;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "SSH [host=" + host + ", user=" + user + ", passwd="
					+ passwd + ", port=" + port +"]";
		}
		
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CameraBase [dirPrefix=" + dirPrefix + ", dirSuffix="
				+ dirSuffix + ", filePrefix=" + filePrefix + ", fileSuffix="
				+ fileSuffix + ", ssh=" + ssh + "]";
	}
	
	
}
