package com.lc.sofa.core.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import com.lc.sofa.container.util.osgi.URLConverterUtil;
import com.lc.sofa.core.framework.basis.exception.CommonException;


/**
 * 
 * 文件处理工具类
 *      YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class FileUtil {

	/**
	 * 创建目录
	 * 
	 *
	 * @param dir
	 *            目录名称
	 * @throws SOFAException
	 *             创建失败，抛出异常
	 */
	public static void mkDir(String dir) throws CommonException {

		StringTokenizer st = new StringTokenizer(dir, "/");
		String path1 = "";
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") < 0) {
			path1 = "/";
		}
		path1 += st.nextToken() + "/";
		String path2 = path1;
		while (st.hasMoreTokens()) {
			path1 = st.nextToken() + "/";
			path2 += path1;
			File inbox = new File(path2);
			if (!inbox.exists()) {
				boolean b = inbox.mkdir();
				if (!b) {
					throw new CommonException("创建[" + dir + "]目录失败.");
				}
			}
		}
	}

	/**
	 * 目录扫描
	 *  
	 * @param dir
	 *            被扫描的目录
	 * @return String[] 目录下文件名全路径集合，不含子目录
	 * @throws SOFAException
	 *             目录格式不合标准时抛出异常
	 */
	public static String[] scanDir(String dir) throws CommonException {

		 return scanDir(dir, "*", false);
	}

	/**
	 * 返回指定目录下的所有文件集合
	 * 
	 * @param dir
	 * @param isIncludeSubdir
	 *            是否包含子目录
	 * @return
	 * @throws SOFAException
	 */
	public static String[] scanDir(String dir, boolean isIncludeSubdir)
			throws CommonException {
		return scanDir(dir, "*", isIncludeSubdir);
	}

	/**
	 * 目录扫描
	 * 
	 *  Orlando
	 * @param dir
	 *            被扫描的目录
	 * @param isIncludeSubdir
	 *            是否包含子目录
	 * @return String[] 目录下文件名全路径集合
	 * @throws SOFAException
	 *             目录格式不合标准时抛出异常
	 */
	public static String[] scanDir(String dir, String suffix,
			boolean isIncludeSubdir) throws CommonException {

		String p = dir;
		try {
			p = new String(p.getBytes("utf-8"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new CommonException("非法目录格式[" + p + "].");
		}
		File f = new File(p);
		if (!f.isDirectory()) {
			return null;
		}

		List<String> files = null;
		try {
			files = new ArrayList<String>();
			for (int i = 0; i < f.list().length; i++) {
				File ff = f.listFiles()[i];
				if (ff.isDirectory()) { // 子目录
					// 如果包含子目录，就进入子目录
					if (isIncludeSubdir == true) {
						String[] subFiles = scanDir(ff.getPath(), suffix,
								isIncludeSubdir);
						if (subFiles == null) {
							continue;
						} else {
							files.addAll(Arrays.asList(subFiles));
						}
					}
					// 如果不包含子目录，继续
					else {
						continue;
					}
				} else if (suffix.equalsIgnoreCase("*")
						|| ff.getPath().endsWith(suffix)) {
					// 获取文件绝对路径，并添加到文件集合中.
					files.add(ff.getPath());
				}
			}

		} catch (Exception ex) {
			throw new CommonException("文件扫描出错:" + ex.getMessage(), ex);
		}

		if (files.size() == 0) {
			return null;
		}
		String[] temp = new String[files.size()];

		return files.toArray(temp);
	}

	/**
	 * 获取指定目录下指定后缀的文件集合
	 * 
	 *  Orlando
	 * @param dir
	 *            目录名称
	 * @param suffix
	 *            文件后缀，如果是*则表示所有文件
	 * @return 指定后缀的文件集合，包含子目录
	 * @throws SOFAException
	 *             目录格式不合标准时抛出异常
	 */
	public static String[] scanDir(String dir, String suffix)
			throws CommonException {
		return scanDir(dir, suffix, true);
		
	}

	/**
	 * 将源文件的内容拷贝到目标文件，如果目标文件已经存在就先删除目标文件
	 * 
	 *  Orlando
	 * @param srcFile
	 *            源文件
	 * @param tagFile
	 *            目标文件
	 * @throws SOFAException
	 *             IO失败抛出异常
	 */
	public static void copyFileToFile(String srcFile, String tagFile)
			throws CommonException {
		copyFileToFile(srcFile, tagFile, true);
	}

	/**
	 * 将源文件的内容拷贝到目标文件，如果目标文件已经存在就判断是否需要覆盖
	 * 
	 *  Orlando
	 * @param srcFile
	 *            源文件
	 * @param tagFile
	 *            目标文件
	 * @param overwrite
	 *            如果目标文件存在，是否覆盖目标文件，否就不复制
	 * @throws SOFAException
	 *             IO失败抛出异常
	 */
	public static void copyFileToFile(String srcFile, String tagFile,
			boolean overwrite) throws CommonException {

		if(!(new File(srcFile).exists())){
			throw new CommonException("复制文件失败：未找到原文件["+srcFile+"].");
		}
		
		File target = new File(tagFile);
		if (target.exists()) {
			// 如果需要覆盖就删除目标文件，删除目标文件先
			if (overwrite) {
				if (!target.delete()) {
					throw new CommonException(target.getName()
							+ " delete failed.");
				}
			} else {
				// 如果不需要覆盖，就不复制，直接返回
				return;
			}
		}

		
		if(!target.getParentFile().exists()){
			target.getParentFile().mkdirs();
		}
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		int c = 0;

		try {
			bin = new BufferedInputStream(new FileInputStream(srcFile));
			bout = new BufferedOutputStream(new FileOutputStream(tagFile));
			
			byte[] tempbytes = new byte[9024];

			while ((c = bin.read(tempbytes)) != -1) {
				// 复制
				bout.write(tempbytes,0,c);
			}

		} catch (IOException ex) {
			throw new CommonException("拷贝文件失败:" + ex.getMessage(), ex);
		} finally {
			try {
				bin.close();
				bout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 创建目录.
	 * 
	 * @param path
	 *            要创建的路径
	 * @return 路径创建成功返回true,路径创建失败返回false
	 *  lidaolong
	 */
	public static boolean createDir(String path) {

		// 1） 传入的路径path如不存在则创建该路径。
		// 2） 创建失败时返回false，否则返回true
		File file = new File(path);
		if (!file.exists()) {

			return file.mkdirs();
		} else {
			return true;
		}

	}

	/**
	 * 新建或覆盖fileUri文件.
	 * 
	 * @param fileName
	 *            要创建的文件
	 * @param content
	 *            要保存到文件中的内容
	 * @return 文件创建成功返回true,创建失败返回false
	 * @throws IOException
	 *             文件操作异常
	 *  ldl
	 */
	public static boolean createFile(String fileName, String content)
			throws IOException {

		boolean isCreate = false;
		File myFilePath = new File(fileName);// 实例化文件操作对象
		// 第一步:判断文件目录 是否存在,如果不存在,反回false
		if (!myFilePath.getParentFile().exists()) {
			return isCreate;
		}
		// 第二步:判断文件是否存在,如果不存在,则创建
		if (!myFilePath.exists()) {
			isCreate = myFilePath.createNewFile();
			if (!isCreate) {
				return isCreate;
			}
		}
		// 第三步:实例化一个写文件对象,进行写文件操作
		FileWriter resultFile = new FileWriter(myFilePath);
		PrintWriter myFile = new PrintWriter(resultFile);
		String strContent = null == content ? "" : content.toString();

		myFile.println(strContent);
		myFile.close();
		resultFile.close();
		isCreate = true;

		return isCreate;

	}

	/**
	 * 要删除的目录.(当含有子目录时,会一并删除).
	 * 
	 * @param path
	 *            要删除的目录
	 * @return 目录删除成功返回true,目录删除失败返回false
	 */
	public static boolean deleteDir(String path) {

		boolean isDelete = false;
		File f = new File(path);// 定义文件路径
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录 begin if one
			if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除 begin if two
				isDelete = f.delete();
			} else {// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						deleteDir(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
					}
					isDelete = delFile[j].delete();
				}
				isDelete = f.delete();
			}
		} else if (!f.exists()) {
			isDelete = true;
		}
		return isDelete;

	}

	/**
	 * 删除指定的文件.
	 * 
	 * @param fileName
	 *            要删除的文件(全路径)
	 * @return 文件删除成功返回true,文件删除失败返回false
	 *  lidaolong
	 */
	public static boolean deleteFile(String fileName) {

		File file = new File(fileName);// 实例化一个文件对象
		if (file.isDirectory()) {
			return false;
		}
		if (!file.exists()) { // 如果文件不存在,返回true
			return true;
		}

		return file.delete();

	}

	/**
	 * 清空指定目录dir中的文件及子目录.
	 * 
	 * @param path
	 *            要清空的目录(全部径)
	 * @return 目录清空成功返回true,目录清空失败返回false
	 *  lidaolong
	 * @throws SOFAException
	 *             SOFA异常
	 */
	public static void clearDir(String path) throws CommonException {

		File f = new File(path);// 定义文件路径
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录 begin if one

			if (f.listFiles().length != 0) {// 若目录下有文件或子目录则删除 begin if two
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;

				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						deleteDir(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
					}
					if (!delFile[j].delete()) {
						throw new CommonException(
								delFile[j].getName()
										+ " delete failed, directory or files in the directory could be in use.");
					}

				}
			}
		}
		// return isDelete;
	}

	/**
	 * 将源目录或文件src移到goal目录中,如果新的目录下已经存在,则覆盖.
	 * 
	 * @param src
	 *            源目录或文件
	 * @param goal
	 *            目标目录
	 * @return 文件移动成功返回true,失败返回false
	 *  lidaolong
	 * @throws IOException
	 * @throws IOException
	 *             文件操作异常
	 */
	public static boolean move(String src, String goal) throws IOException {

		boolean isTrue = false;
		File resFile = new File(src);
		File distFile = new File(goal);

		if (resFile.exists()) {
			// 判断是文件还是目录
			if (resFile.isDirectory()) {

				FileUtils.moveDirectoryToDirectory(resFile, distFile, true);
			} else {

				FileUtils.moveFileToDirectory(resFile, distFile, true);
			}
			isTrue = true;

		}

		return isTrue;
	}

	/**
	 * 移动文件或目录
	 * 
	 * @param src
	 *            原目录
	 * @param goal
	 *            目标
	 * @param isOverride
	 *            true 如果目标文件存在则覆盖
	 * @return true 成功
	 * @throws IOException
	 *             IOException
	 */
	public static boolean move(String src, String goal, boolean isOverride)
			throws IOException {
		if (isOverride) {
			File f = new File(src);
			if (f.exists()) {
				File goalFile = new File(goal);
				if (goalFile.isDirectory()) {
					File gf = new File(goal + File.separator + f.getName());
					if (gf.exists()) {
						gf.delete();
					}
				} else {
					goalFile.delete();
				}
			}
		}
		return move(src, goal);

	}

	/**
	 * 判断目录或文件filePath是否存在，存在返回true，否则返回false.
	 * 
	 * @param path
	 *            目录或文件地址
	 * @return 判断目录或文件filePath是否存在，存在返回true，否则返回false
	 *  ldl
	 */
	public static boolean isExist(String path) {

		File file = new File(path);// 实例化一个文件对象
		return file.exists();

	}

	/**
	 * 如fileUri为文件则返回该文件的字节数，返回字节数,不存在则返回0.
	 * 
	 * @param fileName
	 *            文件路径
	 * @return 以字节为单位的文件大小
	 *  ldl
	 */
	public static long getFileSize(String fileName) {

		File distFile = new File(fileName);
		if (distFile.isFile()) {
			return distFile.length();
		} else if (distFile.isDirectory()) {
			return FileUtils.sizeOfDirectory(distFile);
		}
		return -1L;

	}

	/**
	 * 将文件file的文件名重命名为newFileName.
	 * 
	 * @param oldFileName
	 *            文件
	 * @param newFileName
	 *            新文件名
	 * @return 重命名成功返回true,失败返回false
	 */
	public static boolean rename(String oldFileName, String newFileName) {

		boolean isTrue = false;
		File oldFile = new File(oldFileName);
		File newFile = new File(newFileName);

		if (!newFile.exists()) {
			isTrue = oldFile.renameTo(newFile);
		}

		return isTrue;

	}

	/**
	 * 本地某个目录下的文件列表（不递归）
	 * 
	 * @param path
	 *            ftp上的某个目录
	 * @param suffix
	 *            文件的后缀名（比如.mov.xml)
	 * @return 文件名称列表
	 */
	public static String[] listFilebySuffix(String path, String suffix) {

		IOFileFilter fileFilter1 = new SuffixFileFilter(suffix);
		IOFileFilter fileFilter2 = new NotFileFilter(
				DirectoryFileFilter.INSTANCE);
		FilenameFilter filenameFilter = new AndFileFilter(fileFilter1,
				fileFilter2);
		return new File(path).list(filenameFilter);

	}

	/**
	 * 查找出满足条件的文件对象.
	 * 
	 * @param path
	 *            目录
	 * @param suffix
	 *            查询的文件后缀
	 * @return 满足条件的文件对象
	 *  lidaolong
	 */
	public static List<File> findFile(String path, String suffix) {

		List<File> allFiles = new ArrayList<File>();

		File file = new File(path);

		if (!file.exists()) {
			return null;
		}

		for (File tmpFile : file.listFiles()) {
			if (tmpFile.isDirectory()) {
				allFiles.addAll(findFile(tmpFile, suffix));
				continue;
			}
			if (tmpFile.getName().endsWith(suffix)) {
				allFiles.add(tmpFile);
			}
		}

		return allFiles;
	}

	/**
	 * 查找出满足条件的文件对象.
	 * 
	 * @param file
	 *            根目录
	 * @param suffix
	 *            后缀
	 * @return 满足条件的文件对象
	 *  jiangjin
	 */
	public static List<File> findFile(File file, String suffix) {

		List<File> allFiles = new ArrayList<File>();

		for (File tmpFile : file.listFiles()) {
			if (tmpFile.isDirectory()) {
				allFiles.addAll(findFile(tmpFile, suffix));
				continue;
			}

			if (tmpFile.getName().endsWith(suffix)) {
				allFiles.add(tmpFile);
			}
		}

		return allFiles;
	}

	/**
	 * 返回类路径.
	 * 
	 * @return 返回类的绝对路径
	 *  ldl
	 */
	public static String getSrcPath() {

		return FileUtil.class.getClassLoader().getResource("").getPath()
				.substring(1);

	}

	/**
	 * 保存文件.
	 * 
	 * @param fileName
	 *            文件路径
	 * @param content
	 *            要保存的内容
	 * @return 文件的绝对路径
	 *  ldl
	 * @throws IOException
	 *             文件操作异常
	 * @throws SOFAException
	 *             SOFA异常
	 */
	public static String saveFile(String fileName, String content)
			throws IOException, CommonException {

		// 1） fileUri文件如不存在则创建。
		// 2） 将content保存到fileUri文件中。

		File file = new File(fileName);
		if (!file.exists()) {// 当不存在时,创建该文件
			if (!file.createNewFile()) {
				throw new CommonException(file.getName() + " save failed.");
			}
		}
		// 定义输出流,进行写文件操作
		FileOutputStream out = new FileOutputStream(file, true);
		out.write(content.getBytes("utf-8"));
		out.close();

		return file.getAbsolutePath();

	}

	/**
	 * 复制文件.
	 * 
	 * @param src
	 *            源路径
	 * @param dest
	 *            目标路径
	 * @return 是否成功
	 *  ldl
	 * @throws IOException
	 *             文件操作异常
	 */
	public static boolean copyFile(String src, String dest) throws IOException {

		// 根据源路径和目标路径初始化文件
		File resFile = new File(src);
		File distFile = new File(dest);
		try {
			// 判断复制的是一个文件还是一个目录
			if (resFile.isDirectory()) {
				FileUtils.copyDirectoryToDirectory(resFile, distFile);
			} else {
				FileUtils.copyFileToDirectory(resFile, distFile, true);
			}
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * 使用utf-8格式读文件内容
	 * 
	 * @param path
	 *            文件路径
	 * @return 文件里的内容
	 * @throws IOException
	 *             文件操作异常
	 *  ldl
	 */
	public static String fileReader(String path) throws IOException {

		return fileReader(path, "utf-8");
	}

	/**
	 * 使用指定编码格式，读取指定路径的文件
	 * 
	 * @param path
	 *            文件路径
	 * @param charsetName
	 *            编码格式
	 * @return 文件内容
	 * @throws IOException
	 *             IO异常
	 *  
	 */
	public static String fileReader(String path, String charsetName)
			throws IOException {

		StringBuffer sb = new StringBuffer();
		final int READ_SIZE = 1024;
		File file = new File(path);

		if (!file.exists() || file.isDirectory()) { // 当文件不存在时或为目录时抛出文件不存在异常
			throw new FileNotFoundException();
		}
		FileInputStream fis = new FileInputStream(file);
		try {
			byte[] buf = new byte[READ_SIZE];
			while ((fis.read(buf)) != -1) {
				sb.append(new String(buf, charsetName));
				buf = new byte[READ_SIZE];// 重新生成，避免和上次读取的数据重复
			}
		} finally {
			fis.close();
		}
		return sb.toString();
	}
	
	/**
	 * 将Bundle资源路径协议[bundleresource://]转换为文件系统路径协议，在调用Bundle#getResource(String)时需要使用该方法对其返回的URL进行转换。
	 * @param url bundleresource://协议地址
	 * @return 文件系统路径[file://c:/temp/]
	 */
	public static URL toFileURL(URL url){
		return URLConverterUtil.toFileUrl(url);
	}
}
