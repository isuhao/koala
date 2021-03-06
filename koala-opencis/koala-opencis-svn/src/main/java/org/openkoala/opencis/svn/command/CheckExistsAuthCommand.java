package org.openkoala.opencis.svn.command;

import java.io.IOException;
import java.util.List;

import org.openkoala.opencis.api.Project;
import org.openkoala.opencis.support.CommonUtil;
import org.openkoala.opencis.support.SvnConfig;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.Session;

/**
 * 检测重复的授权
 * @author zjh
 *
 */
public class CheckExistsAuthCommand extends SvnCommand {

	private List<String> userNames;

    private String role;
    
	public CheckExistsAuthCommand() {
		// TODO Auto-generated constructor stub
	}

	public CheckExistsAuthCommand(List<String> userNames, String role,SvnConfig config, Project project) {
		super(config, project);
		this.userNames = userNames;
		this.role = role;
	}

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		String strUsers = CommonUtil.ConvertGroupUserListToString(userNames);
		String strCommand = "grep '" + project.getProjectName() + "_" +  role + "=rw' " + storePath + "authz"; 
		return strCommand;
	}

	@Override
	public void doWork(Connection connection, Session session) {
		// TODO Auto-generated method stub
		try {
			String stderr = readOutput(session.getStdout());
			//如果为空表示没有
			if(!"".equals(stderr)){
				throw new RuntimeException("重复授权配置");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("检测重复授权时发生异常");
		} 
	}
}
