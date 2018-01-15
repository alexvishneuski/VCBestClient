/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.github.alexvishneuski.customerbackend.versionchecker;

import com.github.alexvishneuski.vkbestclient.domain.AppVersion;
import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO make separate json doc with versionNumber, access throuth interface
//TODO read text file in Servlet (with app version)
public class AppVersionCheckerServlet extends HttpServlet {


    private Integer appVersionId = 1;

    @Override
    public void doGet(final HttpServletRequest pRequest, final HttpServletResponse pResponse)
            throws IOException {
        pResponse.setContentType("application/json");

        final AppVersion version = new AppVersion();

        version.setId(appVersionId);

        //TODO what is faster?

        //1
        new Gson().toJson(version, pResponse.getWriter());

        //2
//        final String versionAsString = new Gson().toJson(version);
//        pResponse.getWriter().print(versionAsString);
    }


    @Override
    public void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse)
            throws IOException {
        Integer id = Integer.valueOf(pRequest.getParameter("version"));
        pResponse.setContentType("application/json");
        final AppVersion version = new AppVersion();
        version.setId(appVersionId);
        new Gson().toJson(version, pResponse.getWriter());

    }
}
