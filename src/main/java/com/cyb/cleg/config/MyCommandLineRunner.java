package com.cyb.cleg.config;

import com.cyb.authority.domain.User;
import com.cyb.authority.service.UserService;
import com.cyb.cleg.domain.Team;
import com.cyb.cleg.domain.TeamMember;
import com.cyb.cleg.service.TeamMemberServices;
import com.cyb.cleg.service.TeamServices;
import com.cyb.chat.service.CybTeamChatWSService;
import com.cyb.common.result.R;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    Log log= LogFactory.getLog(MyCommandLineRunner.class);

    @Autowired
    private CybTeamChatWSService cybTeamChatWSService;

    @Autowired
    private TeamServices teamServices;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamMemberServices teamMemberServices;

    @Override
    public void run(String... args) throws Exception {

        //注册Team、user信息到chatServer
        registerTeam();
    }

    /**
     * 注册Team、user信息到chatServer
     */
    private void registerTeam(){

        List<Team> teams = teamServices.selectSelective(null);
        if(null != teams && !teams.isEmpty()){

            for(Team team : teams){

                TeamMember teamMemberParam = new TeamMember();
                teamMemberParam.setTeamId(team.getId());
                List<TeamMember> teamMembers = teamMemberServices.selectSelective(teamMemberParam);

                if(null != teamMembers && !teamMembers.isEmpty()){

                    List<String> userIdList = new ArrayList<>();
                    List<String> userNameList = new ArrayList<>();
                    for(TeamMember teamMember : teamMembers){

                        userIdList.add(teamMember.getUserId());
                        User user = userService.selectByPrimaryKey(teamMember.getUserId());
                        if(user != null){
                            userNameList.add(user.getUserName());
                        }else {
                            userNameList.add("none");
                        }
                    }

                    R r = cybTeamChatWSService.initRegister(team.getId(), userIdList, userNameList);
                    log.info("register team " + team.getId() + " " + r.getMsg());
                }
            }
        }
    }
}
