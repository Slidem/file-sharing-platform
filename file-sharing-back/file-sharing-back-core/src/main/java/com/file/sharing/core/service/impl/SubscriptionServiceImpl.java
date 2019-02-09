package com.file.sharing.core.service.impl;

import com.file.sharing.core.business.UserBusiness;
import com.file.sharing.core.entity.Subscription;
import com.file.sharing.core.exception.UserNotFoundException;
import com.file.sharing.core.objects.UserInfo;
import com.file.sharing.core.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Andrei Aioanei
 * @created 02.02.2019
 */
@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private UserBusiness userBusiness;

    @Autowired
    public SubscriptionServiceImpl(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }
}
