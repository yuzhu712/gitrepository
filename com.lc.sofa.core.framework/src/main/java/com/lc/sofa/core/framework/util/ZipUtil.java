package com.lc.sofa.core.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.lc.sofa.core.framework.basis.exception.CommonException;
import com.lc.sofa.core.framework.util.FileUtil;

/**
 * 
 * ZIP文件操作工具类
 * @author  admin
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public class ZipUtil {

	 private static final int BUFFER_SIZE = 1024;

	    /**
	     * 将字符串压缩为二进制数组 如果要将结果转换为字符串，new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)
	     * 
	     * @param sourceString
	     *            待压缩的字符串
	     * @return 二进制数组
	     * @throws IOException
	     *             IO异常
	     * @author jiangjin
	     */
	    public static byte[] compress(StringBuilder sourceString) throws IOException {

		if (sourceString == null || sourceString.length() == 0) {
		    return null;
		}

		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;

		try {
		    out = new ByteArrayOutputStream();
		    zout = new ZipOutputStream(out);
		    zout.putNextEntry(new ZipEntry("0"));
		    zout.write(sourceString.toString().getBytes());
		    zout.closeEntry();
		    compressed = out.toByteArray();
		} catch (IOException e) {
		    compressed = null;
		} finally {
		    if (zout != null) {
			zout.close();
		    }
		    if (out != null) {
			out.close();
		    }
		}

		return compressed;

	    }

	    /**
	     * 将压缩过的二进制数组解压为字符串
	     * 
	     * @param zip
	     *            二进制数组
	     * @return 字符串
	     * @throws IOException
	     *             IO异常
	     * @author jiangjin
	     */
	    public static StringBuilder decompress(byte[] zip) throws IOException {

		if (zip == null || zip.length == 0) {
		    return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		StringBuilder decompressed;
		try {
		    out = new ByteArrayOutputStream();
		    in = new ByteArrayInputStream(zip);
		    zin = new ZipInputStream(in);
		    zin.getNextEntry();

		    byte[] buffer = new byte[BUFFER_SIZE];
		    int offset = -1;

		    while ((offset = zin.read(buffer)) != -1) {
			out.write(buffer, 0, offset);
		    }

		    decompressed = new StringBuilder(out.toString());
		} catch (IOException e) {
		    decompressed = null;
		} finally {
		    if (zin != null) {
			zin.close();
		    }
		    if (in != null) {
			in.close();
		    }
		    if (out != null) {
			out.close();
		    }
		}

		return decompressed;

	    }

	    /**
	     * 将folder下的文件及子目录压缩到toZipFile.
	     * 
	     * @param folder
	     *            待压缩的文件目录
	     * @param toZipFile
	     *            压缩后的zip文件
	     * @return 是否压缩成功
	     * @author ldl
	     */
	    public static boolean compressFile(String folder, String toZipFile) {

		try {
		    // 当不存在时返回false
		    if (!FileUtil.isExist(folder)) {
			return false;
		    }

		    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(toZipFile));
		    File f = new File(folder);
		    zip(out, f, "");// 递归压缩方法
		    out.close();
		} catch (Exception ex) {
		    return false;
		}
		return true;
	    }

	    /**
	     * 解压缩.
	     * Orlando 这个方法存在问题不，原因没有找到，推荐替代方法ZipUtil.unZip
	     * @param zipFileName
	     *            压缩的zip文件
	     * @param toFolder
	     *            解压到目标目录
	     * @return 解压缩是否成功
	     * @author ldl
	     */
	    @Deprecated
	    public static boolean decompressFile(String zipFileName, String toFolder) {

		boolean flag = true;

		try {
		    // 当不存在这个压宿文件时,返回false
		    if (!FileUtil.isExist(zipFileName)) {
			return false;
		    }
		    
		    if(toFolder != null && toFolder.trim().length() > 0){
			File f = new File(toFolder);
			if (!f.exists()){
				if(!f.mkdir()){
			    throw new CommonException(f.getName() + " create failed.");
				}
			}
		    }

		    ZipInputStream in = new ZipInputStream(new FileInputStream(zipFileName));
		    
		    // 获取ZipInputStream中的ZipEntry条目，一个zip文件中可能包含多个ZipEntry，
		    // 当getNextEntry方法的返回值为null，则代表ZipInputStream中没有下一个ZipEntry，
		    // 输入流读取完成；
		    ZipEntry z ;
		    while ((z = in.getNextEntry()) != null) {		
			
			if (z.isDirectory()) {
			    String name = z.getName();
			    
			    name = name.substring(0, name.length() - 1);
			    
			    if(name.length() == 0){
				continue;
			    }
			    
			    File f = new File(toFolder + File.separator + name);
			    if(!f.mkdir()){
				throw new CommonException(f.getName() + " create failed");
			    }

			} else {
			    File f = new File(toFolder + File.separator + z.getName());
			    if(!f.createNewFile()){
				throw new CommonException(f.getName() + " create failed");
			    }
			    FileOutputStream out = new FileOutputStream(f);
			    int b;
			    while ((b = in.read()) != -1) {
				out.write(b);
			    }
			    out.close();
			}
		    }
		    in.close();
		} catch (Exception e) {
		    e.printStackTrace();
		    return false;
		}

		return flag;
	    }

	    /**
	     * 递归压缩方法.
	     * 
	     * @param out
	     *            压缩包输出流
	     * @param f
	     *            需要压缩的文件
	     * @param baseString
	     *            压缩的路径
	     * @throws Exception
	     *             压缩文件抛出的异常
	     * @author ldl
	     */
	    private static void zip(ZipOutputStream out, File f, String baseString) throws Exception {

		if (f.isDirectory()) { // 如果是文件夹，则获取下面的所有文件
		    File[] fl = f.listFiles();
		    out.putNextEntry(new ZipEntry(baseString + "/"));// 此处要将文件写到文件夹中只用在文件名前加"/"再加文件夹名
		    String str = baseString.length() == 0 ? "" : baseString + "/";
		    for (int i = 0; i < fl.length; i++) {
			zip(out, fl[i], str + fl[i].getName());
		    }
		} else { // 如果是文件，则压缩
		    out.putNextEntry(new ZipEntry(baseString)); // 生成下一个压缩节点
		    FileInputStream in = new FileInputStream(f); // 读取文件内容
		    
		    byte[] buf = new byte[4096];
		    int b;
		    while ((b = in.read(buf)) != -1) {
		    	out.write(buf,0,b); // 写入到压缩包
		    	out.flush();
		    }
		    in.close();
		}
	    }
	    
	    /**解压缩组件包，不支持包内文件有中文名称的
		 * @param localfileName
		 * @param repositoryPath
		 * @throws BizException
		 * @author Orlando
		 */
		@SuppressWarnings("unchecked")
		public static void unZip(String localfileName, String repositoryPath) throws CommonException {
			BufferedOutputStream bos = null;
			BufferedInputStream bis = null;
			FileOutputStream fos = null;
			try {
				ZipFile zipFile = new ZipFile(localfileName);
				Enumeration<ZipEntry> emu = (Enumeration<ZipEntry>) zipFile.entries();
				while (emu.hasMoreElements()) {
					ZipEntry entry = emu.nextElement();
					// 会把目录作为一个file读出一次，所以只建立目录就可以，之下的文件还会被迭代到。
					if (entry.isDirectory()) {
						new File(repositoryPath + new String(entry.getName())).mkdirs();
						continue;
					}
					bis = new BufferedInputStream(zipFile
							.getInputStream(entry));
					File file = new File(repositoryPath + entry.getName());
					// 加入这个的原因是zipfile读取文件是随机读取的，这就造成可能先读取一个文件
					// 而这个文件所在的目录还没有出现过，所以要建出目录来。
					File parent = file.getParentFile();
					if (parent != null && (!parent.exists())) {
						parent.mkdirs();
					}
					fos = new FileOutputStream(file);
					bos = new BufferedOutputStream(fos, BUFFER);

					int count;
					byte data[] = new byte[BUFFER];
					while ((count = bis.read(data, 0, BUFFER)) != -1) {
						bos.write(data, 0, count);
					}
					bos.flush();
					bos.close();
					bis.close();
				}
				zipFile.close();

			} catch (Exception ex) {
				throw new CommonException(ex.getMessage(),ex);
			}finally{
				try {
					fos.close();
					bos.close();
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}
		static final int BUFFER = 2048;
}
