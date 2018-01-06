package com.file.sharing.core.objects;

import java.time.Instant;
import java.util.Optional;

import com.file.sharing.core.objects.file.ItemActionType;

/**
 * @author Alexandru Mihai
 * @created Dec 28, 2017
 * 
 */
public interface ItemActionInfo {

	Optional<BasicItemInfo> getBasicItemInfo();

	Instant getActionTime();

	ItemActionType getActionType();

}
