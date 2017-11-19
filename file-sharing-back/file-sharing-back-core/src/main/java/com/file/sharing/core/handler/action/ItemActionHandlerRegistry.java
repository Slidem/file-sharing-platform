package com.file.sharing.core.handler.action;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.core.exception.ItemEventHandlerNotFoundException;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
@Component
public class ItemActionHandlerRegistry {

	
	private final Map<Class<?>, ItemActionHandler<?>> handlerMap = new HashMap<>();

	private List<ItemActionHandler<?>> handlers;
	
	@Autowired
	public ItemActionHandlerRegistry(List<ItemActionHandler<?>> handlers) {
		this.handlers = handlers;
	}
	
	
	@PostConstruct
	private void initMap() {
		handlerMap.putAll(handlers.stream().collect(toMap(ItemActionHandler::getActionClass, identity())));
	}
	
	
	@SuppressWarnings("unchecked")
	public <T extends ItemAction> ItemActionHandler<T> getHandler(Class<T> eventClass) {
		ItemActionHandler<?> handler = handlerMap.get(eventClass);
		if (handler == null) {
			throw new ItemEventHandlerNotFoundException();
		}

		return (ItemActionHandler<T>) handler;
	}
	
}

