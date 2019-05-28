/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.remote.player;

import java.util.List;

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