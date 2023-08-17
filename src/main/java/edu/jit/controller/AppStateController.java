package edu.jit.controller;

import edu.jit.service.IAppStateService;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

/**
 * nullController接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Api(value = "null" , tags = "null")
@RequestMapping(value = "" , produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class AppStateController {

    @Resource
    private IAppStateService appStateService;

}
