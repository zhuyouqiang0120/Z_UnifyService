package com.zens.unify.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用工具类
 * @author vector
 * @time 2014年9月3日 上午11:31:54
 *
 */
public class FileUtils {

	/**
	 * 获取路径下所有文件名
	 * 
	 * @param path
	 * @return
	 */
	public static String[] getFileNames(String path) {
		File file = new File(path);
		String[] name = file.list();
		return name;
	}

	/**
	 * 获取路径下所有文件夹名
	 * 
	 * @param path
	 * @return
	 */
	public static String[] getDirNames(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		List<String> dirs = new ArrayList<String>();
		for (File dir : files) {
			if (dir.isDirectory() && !dir.isHidden()) {
				dirs.add(dir.getName());
			}
		}
		return dirs.toArray(new String[0]);
	}

	/**
	 * 复制文件目录
	 * 
	 * @param sourceDirPath
	 *            源文件目录路径
	 * @param targetDirPath
	 *            目标文件目录路径
	 * @return 是否复制成功
	 * @throws IOException
	 * 
	 */
	public static boolean copyDir(String sourceDirPath, String targetDirPath)
			throws IOException {
		boolean bool = false;
		targetDirPath = checkFilePathEndSep(targetDirPath);
		// 创建目标文件夹
		dirExists(targetDirPath);
		// 删除该文件夹下所有文件
		del(targetDirPath);
		// 获取源文件夹当前下的文件或目录
		File sourceFileDir = new File(sourceDirPath);
		if (sourceFileDir.exists()) {
			bool = true;
			File[] files = sourceFileDir.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					copyFile(file,new File(targetDirPath + file.getName()));
				}
				if (file.isDirectory()) {
					String fileName = file.getName();
					// 复制目录
					String sourceDir = sourceDirPath + File.separator + fileName;
					String targetDir = targetDirPath + File.separator + fileName;
					copyDirectiory(sourceDir, targetDir);
				}
			}
		}
		return bool;
	}

	/**
	 * 检查文件夹末尾是否有“/”，没有添加
	 * 
	 * @param destDir
	 * @return
	 */
	public static String checkFilePathEndSep(String destDir) {
		// 保证文件夹路径最后是"/"或者"\"
		char lastChar = destDir.charAt(destDir.length() - 1);
		if (lastChar != '/' && lastChar != '\\') {
			destDir += "/";
		}
		return destDir;
	}

	/**
	 * 检查文件夹是否存在,不存在创建文件夹
	 * 
	 * @param filePath
	 */
	public static void dirExists(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 读取文件中内容
	 * 
	 * @param path
	 *            路径
	 * @return
	 * @throws IOException
	 */
	public static String readFileToString(String path) throws IOException {
		String resultStr = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			byte[] inBuf = new byte[2000];
			int len = inBuf.length;
			int off = 0;
			int ret = 0;
			while ((ret = fis.read(inBuf, off, len)) > 0) {
				off += ret;
				len -= ret;
			}
			resultStr = new String(new String(inBuf, 0, off, "GBK").getBytes());
		} finally {
			if (fis != null)
				fis.close();
		}
		return resultStr;
	}

	/**
	 * 文件转成字节数组
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFileToBytes(String path) throws IOException {
		byte[] b = null;
		InputStream is = null;
		File f = new File(path);
		try {
			is = new FileInputStream(f);
			b = new byte[(int) f.length()];
			is.read(b);
		} finally {
			if (is != null)
				is.close();
		}
		return b;
	}

	/**
	 * 将byte写入文件中
	 * 
	 * @param fileByte
	 * @param filePath
	 * @throws IOException
	 */
	public static void byteToFile(byte[] fileByte, String filePath)
			throws IOException {
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(filePath));
			os.write(fileByte);
			os.flush();
		} finally {
			if (os != null)
				os.close();
		}
	}

	/**
	 * 折分数组
	 * 
	 * @param ary
	 * @param subSize
	 * @return
	 */
	public static List<List<Object>> splitAry(Object[] ary, int subSize) {
		int count = ary.length % subSize == 0 ? ary.length / subSize
				: ary.length / subSize + 1;

		List<List<Object>> subAryList = new ArrayList<List<Object>>();

		for (int i = 0; i < count; i++) {
			int index = i * subSize;

			List<Object> list = new ArrayList<Object>();
			int j = 0;
			while (j < subSize && index < ary.length) {
				list.add(ary[index++]);
				j++;
			}

			subAryList.add(list);
		}

		return subAryList;
	}

	/**
	 * @param mobile
	 * @return
	 */
	public static String ArrayToString(Object[] mobile) {
		String destId = "";
		for (Object phone : mobile) {
			destId += " " + (String) phone;
		}
		return destId.trim();
	}

	/**
	 * 获取所有文件名
	 * 
	 * @return
	 */
	public static String getFileNameToString(String path) {
		String[] fileName = getDirNames(path);
		String result = "";
		int length = fileName.length;
		for (int i = 0; i < length; i++) {
			result += fileName[i];
			if (i < length - 1) {
				result += ",";
			}
		}
		return result;
	}
	/**
	 * 写入文件
	 * @param inputStream
	 * @param savePath
	 * @return
	 */
	public static boolean writeToFile(InputStream inputStream,String savePath){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"gbk"));
			File file = new File(savePath);
			if(file.exists()){
				file.delete();
			}
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw=new BufferedWriter(fw);
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				bw.append(inputLine);
				bw.newLine();
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	/**
	 * 写入文件
	 * @param content 文件内容
	 * @param savePath
	 * @return
	 */
	public static boolean writeToFile(String content,String savePath){
		String[] contents = content.split("<br/>");
		try {
			File file = new File(savePath);
			if(file.exists()){
				file.delete();
			}
			BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
			for(String str : contents){
				bw.append(str);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * 获取文件后缀名
	 * 
	 * @param fileName
	 * @return 如：.jpg、.gif
	 */
	public static String getFileSuffix(String fileName) {
		int lastIndexOfDot = fileName.lastIndexOf('.');
		int fileNameLength = fileName.length();
		return fileName.substring(lastIndexOfDot, fileNameLength);
	}
	/**
	 * 获取文件后缀名不要“.”
	 * 
	 * @param fileName
	 * @return 如jpg、gif
	 */
	public static String getSuffix(String fileName) {
		int lastIndexOfDot = fileName.lastIndexOf('.');
		int fileNameLength = fileName.length();
		return fileName.substring(lastIndexOfDot+1, fileNameLength);
	}
	/**
	 * 返回文件名
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName){
		int lastIndexOfDot = fileName.lastIndexOf('.');
		int lastIndexOfSep = fileName.lastIndexOf("/");
		if(!fileName.startsWith("/")){
			lastIndexOfSep = fileName.lastIndexOf("\\");
		}
		lastIndexOfSep = lastIndexOfSep == -1 ? 0 : lastIndexOfSep;
		lastIndexOfDot = lastIndexOfDot == -1 ? fileName.length() : lastIndexOfDot;
		return fileName.substring(lastIndexOfSep, lastIndexOfDot);
	}
	/**
	 * 复制文件
	 * @param sourceFile 源文件
	 * @param targetFile 目标文件
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	/**
	 * 复制文件目录
	 * @param sourceDir 源文件目录
	 * @param targetDir 目标文件目录
	 * @throws IOException
	 */
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] files = (new File(sourceDir)).listFiles();
		for(File file : files){
			if (file.isFile()) {
				// 源文件
				File sourceFile = file;
				// 目标文件
				File targetFile = new File(
						new File(targetDir).getAbsolutePath() + File.separator
								+ file.getName());
				copyFile(sourceFile, targetFile);
			}
			if (file.isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file.getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file.getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * 
	 * @param srcFileName 源文件名
	 * @param destFileName 目标文件名
	 * @param srcCoding 源文件编码
	 * @param destCoding 目标文件编码
	 * @throws IOException
	 */
	public static void copyFile(File srcFileName, File destFileName,
			String srcCoding, String destCoding) throws IOException {// 把文件转换为GBK文件
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					srcFileName), srcCoding));
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(destFileName), destCoding));
			char[] cbuf = new char[1024 * 5];
			int len = cbuf.length;
			int off = 0;
			int ret = 0;
			while ((ret = br.read(cbuf, off, len)) > 0) {
				off += ret;
				len -= ret;
			}
			bw.write(cbuf, 0, off);
			bw.flush();
		} finally {
			if (br != null)
				br.close();
			if (bw != null)
				bw.close();
		}
	}

	/**
	 * 删除文件夹下所有内容
	 * @param filepath 文件路径
	 * @throws IOException
	 */
	public static void del(String filepath) throws IOException {
		File f = new File(filepath);// 定义文件路径
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
			if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
				f.delete();
			} else {// 若有则把文件放进数组，并判断是否有下级目录
				File[] delFile = f.listFiles();
				for (File file : delFile) {
					if (file.isDirectory()) {
						del(file.getAbsolutePath());// 递归调用del方法并取得子目录路径
					}
					file.delete();// 删除文件
				}
			}
		}
	}
}