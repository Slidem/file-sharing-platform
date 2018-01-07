package com.file.sharing.rest.factory;

import com.file.sharing.core.objects.PageResult;
import com.file.sharing.core.objects.file.BasicFileInfo;
import com.file.sharing.rest.dto.PageResultDTO;
import com.file.sharing.rest.dto.file.BasicFileInfoDTO;

/**
 * @author Alexandru Mihai
 * @created Jan 7, 2018
 * 
 */
public interface PageResultFactory {

	PageResultDTO<BasicFileInfoDTO> toBasicFileInfoResultDto(PageResult<BasicFileInfo> result);

}
