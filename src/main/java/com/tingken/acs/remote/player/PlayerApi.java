/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player;

import java.util.List;

import com.tingken.acs.remote.player.pojo.GroupsResult;
import com.tingken.acs.remote.player.pojo.PlayerResult;
import com.tingken.acs.remote.player.pojo.TermIdListResult;
import com.tingken.acs.remote.player.pojo.TermStateListResult;
import com.tingken.acs.remote.player.pojo.TextPlayInfo;

/**
 * This interface is to access player-system and get data, control it
 * to play text.
 */
public interface PlayerApi {

    PlayerResult login();

    TermIdListResult getTermIdList();

    TermStateListResult getTermStateList(List<Integer> idList);

    GroupsResult getGroups();

    PlayerResult playText(TextPlayInfo requestBody);

}