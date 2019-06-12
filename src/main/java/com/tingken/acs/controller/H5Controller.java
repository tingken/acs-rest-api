/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.tingken.acs.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The purpose of this class is to map h5 related path to the static
 * page.
 */
@Controller
public class H5Controller {

    @Value("${:classpath:/static/index.html}")
    private Resource index;

    @GetMapping(value = { "/admin/*", "/operator/*", "/login" }, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ResponseEntity actions() throws IOException {
        return ResponseEntity.ok(new InputStreamResource(index.getInputStream()));
    }
}
