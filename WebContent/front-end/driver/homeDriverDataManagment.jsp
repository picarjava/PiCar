<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.driver.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>homeDriverDataManagment.jsp</title>
<%--     <jsp:include page="/regna-master/head.jsp" /> --%>
    <jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
</head>
<style>
style.css
今天
下午6:31

黃增銓上傳了 1 個項目
CSS 樣式表
style.css
/*
 *
 * Template Name: Andia
 * Template URI: http://azmind.com
 * Description: Andia - Responsive Agency Template
 * Author: Anli Zaimi
 * Author URI: http://azmind.com
 *
 */


body {
    background: #c1c1c1;
    text-align: center;
    font-family: 'Open Sans', Helvetica, Arial, sans-serif;
    color: #888;
    font-size: 12px;
}

.violet { color: #268667; }

a {
    color: #268667;
    text-decoration: none;
    -o-transition: all .3s;
    -moz-transition: all .3s;
    -webkit-transition: all .3s;
    -ms-transition: all .3s;
}

a:hover { color: #888; text-decoration: none; }

strong { font-weight: bold; }


/* ----- Header ----- */

.header .navbar {
    margin-bottom: 0;
}

.header .navbar-inner {
    position: fixed;
    background: #ddd;
    border: 0;
    -moz-border-radius: 0;
    -webkit-border-radius: 0;
    border-radius: 0;
    -moz-box-shadow: none;
    -webkit-box-shadow: none;
    box-shadow: none;
    left: 200px;
    right: 200px;
    z-index: 997;
    top: 0;
    height: 105px;
}

.header h1 {
    float: left;
    margin: 0;
    text-align: left;
}

.header a.brand {
    display: inline-block;
    text-indent: -9999px;
    width: 280px;
    height: 63px;
    padding: 30px 0;
    background: url(../img/logo.png) 20px center no-repeat;
}

.header ul.nav {
    font-size: 14px;
    text-transform: uppercase;
}

.header ul.nav li a {
    padding: 30px 20px 10px 20px;
    color: #5d5d5d;
    text-shadow: none;
}

.header ul.nav li.current-page a {
    padding-top: 25px;
    border-top: 5px solid #268667;
    background: #999999;
}

.header ul.nav li a:hover {

    background: #268667;
    color: #e6e6e6;
}

.header ul.nav li a i {
    line-height: 35px;
        color: #5d5d5d;
}

.header ul.nav li a:hover i { color: #c1c1c1; }


/* ----- Slider ----- */

.slider {
        background-color: #b9b8b8;
    margin: 0 auto;
   /*  background: #f8f8f8 url(../img/pattern.jpg) left top repeat; */
    -moz-box-shadow:
        0 5px 15px 0 rgba(0,0,0,.05) inset,
        0 -5px 15px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 5px 15px 0 rgba(0,0,0,.05) inset,
        0 -5px 15px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 5px 15px 0 rgba(0,0,0,.05) inset,
        0 -5px 15px 0 rgba(0,0,0,.05) inset;
}

.flexslider {
    margin-top: 45px;
    margin-bottom: 55px;
    border: 6px solid #f1f1f1;
    -moz-border-radius: 0;
    -webkit-border-radius: 0;
    border-radius: 0;
    -moz-box-shadow:
        0 5px 15px 0 rgba(0,0,0,.05),
        0 -5px 15px 0 rgba(0,0,0,.05);
    -webkit-box-shadow:
        0 5px 15px 0 rgba(0,0,0,.05),
        0 -5px 15px 0 rgba(0,0,0,.05);
    box-shadow:
        0 5px 15px 0 rgba(0,0,0,.05),
        0 -5px 15px 0 rgba(0,0,0,.05);
}

.flex-caption {
    position: absolute;
    bottom: 20px;
    max-width: 920px;
    padding: 10px 20px;
    margin: 0;
    background: #1d1d1d; /* browsers that don't support rgba */
    background: rgba(0, 0, 0, .7);
    font-size: 14px;
    line-height: 24px;
    color: #eaeaea;
    text-align: left;
    font-style: italic;
}


/* ----- Presentation ----- */

.presentation {
    margin-top: 30px;
}

.presentation h2 {
    font-family: 'Lobster', cursive;
    font-size: 30px;
    color: #5d5d5d;
}

.presentation p {
    font-size: 18px;
    font-style: italic;
}


/* ----- What we do ----- */

.what-we-do {
    margin-top: 50px;
}

.what-we-do .service {
    padding-bottom: 23px;
    background: #ddd;
    border-bottom: 2px solid #268667;
}

.what-we-do .service:hover {
     box-shadow: 0 5px 15px 0 rgba(0,0,0,.05), 0 1px 25px 0 #759289 inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -o-transition: all .5s;
    -moz-transition: all .5s;
    -webkit-transition: all .5s;
    -ms-transition: all .5s;
}

.what-we-do .service .icon-awesome {
    margin-top: 15px;
    font-size: 50px;
    line-height: 50px;
    color: #268667;
}

.what-we-do .service h4 {
    margin-top: 5px;
    font-family: 'Droid Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #5d5d5d;
    text-transform: uppercase;
    text-shadow: 0 1px 0 rgba(255,255,255,.7);
}

.what-we-do .service p {
    padding-bottom: 10px;
    line-height: 24px;
}

.what-we-do .service a {
    font-family: "Poppins", sans-serif;
    border-radius: 50px;
    padding: 5px 22px;
    background: #f5f5f5;
    color: #268667;
    font-style: italic;
    text-decoration: none;
    -moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border-radius: 50px;
    border: 2px solid #268667;
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
}

.what-we-do .service a:hover {
    font-family: "Poppins", sans-serif;
    border-radius: 50px;
    padding: 5px 22px;
    background: #268667;
    color: #f5f5f5;
    font-style: italic;
    text-decoration: none;
    -moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border-radius: 50px;
    border: 2px solid #268667;
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
}

.what-we-do .service a:hover {
    -moz-box-shadow: none;
    -webkit-box-shadow: none;
    box-shadow: none;
}

.what-we-do .service a:active {
    -moz-box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
}


/* ----- Portfolio ----- */

.portfolio {
    margin-top: 50px;
}

.portfolio-title {
    background: url(../img/line.png) left center repeat-x;
}

.portfolio-title h3 {
    width: 220px;
    margin: 0 auto;
    background: #c1c1c1;
    font-family: 'Lobster', cursive;
    font-size: 24px;
    color: #5d5d5d;
}

.portfolio .work {
    overflow: hidden;
    margin-top: 40px;
    margin-bottom: 40px;
    padding-bottom: 20px;
    background: #ddd;
    border-bottom: 2px solid #268667;
}

.portfolio .work:hover img {
    overflow: hidden;
    opacity: 0.7;
    -o-transition: all .3s;
    -moz-transition: all .3s;
    -webkit-transition: all .3s;
    -ms-transition: all .3s;
    max-width: 105%;
}



.portfolio .work:hover {
    box-shadow:
        0 5px 15px 0 rgba(0,0,0,.05),
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -o-transition: all .5s;
    -moz-transition: all .5s;
    -webkit-transition: all .5s;
    -ms-transition: all .5s;
    max-width: 100%;
}

.portfolio .work .icon-awesome {
    margin-top: 15px;
    font-size: 22px;
    line-height: 22px;
}

.portfolio .work .icon-awesome a {
    display: inline-block;
    padding: 5px 9px;
    background: #268667; 
    color: #c1c1c1;
    -moz-border-radius: 19px;
    -webkit-border-radius: 19px;
    border-radius: 19px;
    -moz-box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
}

.portfolio .work .icon-awesome a:hover {
    background: #5d5d5d;
    -moz-box-shadow: none;
    -webkit-box-shadow: none;
    box-shadow: none;
}

.portfolio .work .icon-awesome a:active {
    -moz-box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
}

.portfolio .work h4 {
    margin-top: 20px;
    font-family: 'Droid Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #5d5d5d;
    text-transform: uppercase;
    text-shadow: 0 1px 0 rgba(255,255,255,.7);
}

.portfolio .work p {
    line-height: 24px;
    font-style: italic;
}


/* ----- Testimonials ----- */

.testimonials {
    margin-top: 50px;
    padding-bottom: 50px;
}

.testimonials-title {
    background: url(../img/line.png) left center repeat-x;
}

.testimonials-title h3 {
    width: 180px;
    margin: 0 auto;
    background: #c1c1c1;
    font-family: 'Lobster', cursive;
    font-size: 24px;
    color: #5d5d5d;
}

.testimonial-list {
    margin-top: 30px;
    text-align: left;
}

.testimonial-list img {
    float: left;
    margin: 10px 0 0 60px;
    border: 3px solid #eaeaea;
}

.testimonial-list p {
    padding: 0 60px 0 150px;
    font-size: 14px;
    line-height: 30px;
    font-style: italic;
}

.testimonial-list .nav-tabs {
    width: 200px;
    float: right;
    border: 0;
}

.testimonial-list .nav-tabs li {
    margin-right: 6px;
}

.testimonial-list .nav-tabs li a {
    width: 12px;
    height: 12px;
    padding: 0;
    background: #eaeaea;
    border: 0;
    -moz-border-radius: 0;
    -webkit-border-radius: 0;
    border-radius: 0;
}

.testimonial-list .nav-tabs li a:hover { border: 0; background: #ddd; }
.testimonial-list .nav-tabs li.active a { background: #268667; }


/* ----- Footer ----- */

footer {
    margin: 0 auto;
    padding-bottom: 10px;
    /* background: #f8f8f8 url(../img/pattern.jpg) left top repeat; */
    -moz-box-shadow: 0 5px 15px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow: 0 5px 15px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 5px 15px 0 rgba(0,0,0,.05) inset;
}

footer .widget {
    margin-top: 20px;
    text-align: left;
}

footer .widget h4 {
    margin-top: 20px;
    font-family: 'Droid Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #5d5d5d;
    text-transform: uppercase;
    text-shadow: 0 1px 0 rgba(255,255,255,.7);
}

footer .widget p {
    line-height: 24px;
}

footer .widget i {
    padding-right: 7px;
}

/* Twitter feed */
.show-tweets {
    margin: 0;
    overflow-y: hidden;
}

.tweet_list {
    height: 10em;
    margin: 0;
    padding: 0;
    overflow-y: hidden;
    list-style: none;
}
	
.tweet_list li {
    height: 100%;
    overflow-y: auto;
    overflow-x: hidden;
    list-style-type: none;
    line-height: 24px;
}
		
.tweet_list .tweet_avatar {
    float: left;
}
		
.tweet_list .tweet_avatar img {
    vertical-align: middle;
}

/* Flickr feed */
.flickr-feed {
    margin: 16px 0 0 0;
    overflow: hidden
}

.flickr-feed li {
    float: left;
    padding: 0 4px 4px 0;
    list-style: none;
}

.flickr-feed li img {
    width: 50px;
    border: 2px solid #eaeaea;
}

.flickr-feed a:hover {
    opacity: 0.7;
}


footer .footer-border {
    margin-top: 30px;
    border-top: 1px dashed #ddd;
}

footer .copyright {
    margin-top: 15px;
    text-align: left;
}

footer .social {
    margin-top: 10px;
    text-align: right;
}

footer .social a { display: inline-block; width: 24px; height: 24px; margin: 0 0 0 8px; vertical-align: middle; }

footer .social a.twitter { background: url(../img/social-icons/twitter.png) left bottom no-repeat; }
footer .social a.dribbble { background: url(../img/social-icons/dribbble.png) left bottom no-repeat; }
footer .social a.rss { background: url(../img/social-icons/rss.png) left bottom no-repeat; }
footer .social a.pinterest { background: url(../img/social-icons/pinterest.png) left bottom no-repeat; }
footer .social a.flickr { background: url(../img/social-icons/flickr.png) left bottom no-repeat; }
footer .social a.forrst { background: url(../img/social-icons/forrst.png) left bottom no-repeat; }
footer .social a.vimeo { background: url(../img/social-icons/vimeo.png) left bottom no-repeat; }
footer .social a.linkedin { background: url(../img/social-icons/linkedin.png) left bottom no-repeat; }
footer .social a.facebook { background: url(../img/social-icons/facebook.png) left bottom no-repeat; }
footer .social a.email { background: url(../img/social-icons/email.png) left bottom no-repeat; }
footer .social a.github { background: url(../img/social-icons/github.png) left bottom no-repeat; }
footer .social a.behance { background: url(../img/social-icons/behance.png) left bottom no-repeat; }
footer .social a.googleplus { background: url(../img/social-icons/googleplus.png) left bottom no-repeat; }
footer .social a.youtube { background: url(../img/social-icons/youtube.png) left bottom no-repeat; }
footer .social a.skype { background: url(../img/social-icons/skype.png) left bottom no-repeat; }
footer .social a.tumblr { background: url(../img/social-icons/tumblr.png) left bottom no-repeat; }

footer .social a:hover { background-position: left top; }


/* ----- Page title ----- */

.page-title {
    margin: 0 auto;
    padding: 30px 0 35px 0;
    background: #f8f8f8 url(../img/pattern.jpg) left top repeat;
    -moz-box-shadow:
        0 5px 15px 0 rgba(0,0,0,.05) inset,
        0 -5px 15px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 5px 15px 0 rgba(0,0,0,.05) inset,
        0 -5px 15px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 5px 15px 0 rgba(0,0,0,.05) inset,
        0 -5px 15px 0 rgba(0,0,0,.05) inset;
    text-align: left;
}

.page-title h2 {
    display: inline;
    margin-left: 10px;
    font-family: 'Lobster', cursive;
    font-size: 24px;
    color: #5d5d5d;
    text-shadow: 0 1px 0 rgba(255, 255, 255, .7);
    vertical-align: middle;
}

.page-title p {
    display: inline;
    margin-left: 5px;
    font-size: 14px;
    font-style: italic;
    vertical-align: middle;
}

.page-title-icon {
    margin-left: 20px;
    font-size: 46px;
    color: #ccc;
    vertical-align: middle;
}


/********** ----- ABOUT PAGE ----- **********/

/* ----- About us text ----- */

.about-us {
    margin-top: 20px;
}

.about-us-text {
    padding: 10px 0;
    text-align: left;
}

.about-us-text h4 {
    margin-top: 25px;
    padding: 0 20px;
    font-family: 'Droid Sans', Helvetica, Arial, sans-serif;
    font-size: 16px;
    color: #5d5d5d;
    text-transform: uppercase;
    text-shadow: 0 1px 0 rgba(255,255,255,.7);
}

.about-us-text p {
    padding: 0 20px;
    line-height: 28px;
    font-size: 13px;
}

/* ----- Meet our team ----- */

.team {
    margin-top: 30px;
}

.team-title {
    background: url(../img/line.png) left center repeat-x;
}

.team-title h3 {
    width: 220px;
    margin: 0 auto;
    background: #c1c1c1;
    font-family: 'Lobster', cursive;
    font-size: 24px;
    color: #5d5d5d;
}

.team-text {
    margin-top: 40px;
    padding-bottom: 20px;
    background: #f8f8f8;
    border-bottom: 2px solid #268667;
}

.team-text:hover img {
    opacity: 0.7;
    -o-transition: all .3s;
    -moz-transition: all .3s;
    -webkit-transition: all .3s;
    -ms-transition: all .3s;
}

.team-text:hover {
    box-shadow:
        0 5px 15px 0 rgba(0,0,0,.05),
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -o-transition: all .5s;
    -moz-transition: all .5s;
    -webkit-transition: all .5s;
    -ms-transition: all .5s;
}

.team-text .social-links {
    margin-top: 15px;
}

.team-text .social-links a { display: inline-block; width: 24px; height: 24px; margin: 0 4px; vertical-align: middle; }

.team-text .social-links a.twitter { background: url(../img/social-icons/twitter.png) left top no-repeat; }
.team-text .social-links a.dribbble { background: url(../img/social-icons/dribbble.png) left top no-repeat; }
.team-text .social-links a.pinterest { background: url(../img/social-icons/pinterest.png) left top no-repeat; }
.team-text .social-links a.flickr { background: url(../img/social-icons/flickr.png) left top no-repeat; }
.team-text .social-links a.forrst { background: url(../img/social-icons/forrst.png) left top no-repeat; }
.team-text .social-links a.vimeo { background: url(../img/social-icons/vimeo.png) left top no-repeat; }
.team-text .social-links a.linkedin { background: url(../img/social-icons/linkedin.png) left top no-repeat; }
.team-text .social-links a.facebook { background: url(../img/social-icons/facebook.png) left top no-repeat; }
.team-text .social-links a.email { background: url(../img/social-icons/email.png) left top no-repeat; }
.team-text .social-links a.behance { background: url(../img/social-icons/behance.png) left top no-repeat; }
.team-text .social-links a.googleplus { background: url(../img/social-icons/googleplus.png) left top no-repeat; }
.team-text .social-links a.youtube { background: url(../img/social-icons/youtube.png) left top no-repeat; }
.team-text .social-links a.skype { background: url(../img/social-icons/skype.png) left top no-repeat; }
.team-text .social-links a.tumblr { background: url(../img/social-icons/tumblr.png) left top no-repeat; }

.team-text .social-links a:hover { background-position: left bottom; }

.team-text h4 {
    margin-top: 20px;
    font-family: 'Droid Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #5d5d5d;
    text-transform: uppercase;
    text-shadow: 0 1px 0 rgba(255,255,255,.7);
}

.team-text p {
    line-height: 24px;
    font-style: italic;
}


/********** ----- CONTACT PAGE ----- **********/

/* ----- Form ----- */

.contact-us {
    margin-top: 20px;
    padding-bottom: 50px;
    text-align: left;
}

.contact-us h4 {
    margin-top: 25px;
    padding: 0 20px;
    font-family: 'Droid Sans', Helvetica, Arial, sans-serif;
    font-size: 16px;
    color: #5d5d5d;
    text-transform: uppercase;
    text-shadow: 0 1px 0 rgba(255,255,255,.7);
}

.contact-us p {
    padding: 0 20px;
    line-height: 28px;
    font-size: 13px;
}

.contact-form {
    padding: 10px 0;
}

.contact-form p {
    margin-top: 15px;
}

.contact-us form {
    margin-top: 25px;
    padding: 0 20px;
}

.contact-us form input, .contact-us form textarea {
    width: 90%;
    height: 24px;
    border: 1px solid #ddd;
    -moz-border-radius: 0;
    -webkit-border-radius: 0;
    border-radius: 0;
    -moz-box-shadow: none;
    -webkit-box-shadow: none;
    box-shadow: none;
    font-family: 'Open Sans', Helvetica, Arial, sans-serif;
    color: #888;
    font-size: 13px;
    font-style: italic;
}

.contact-us form input:focus, .contact-us form textarea:focus {
    border: 1px solid #bbb;
    -moz-box-shadow: none;
    -webkit-box-shadow: none;
    box-shadow: none;
}

.contact-us form textarea {
    height: 160px;
}

.contact-us form button {
    width: 120px;
    height: 30px;
    margin-top: 15px;
    background: #268667;
    border: 0;
    font-family: 'Open Sans', Helvetica, Arial, sans-serif;
    font-size: 13px;
    color: #c1c1c1;
    -moz-box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -o-transition: all .3s;
    -moz-transition: all .3s;
    -webkit-transition: all .3s;
    -ms-transition: all .3s;
}

.contact-us form button:hover {
    -moz-box-shadow: none;
    -webkit-box-shadow: none;
    box-shadow: none;
}

.contact-us form button:active {
    -moz-box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
}

.contact-us form label {
    margin-top: 10px;
    font-size: 13px;
}

.contact-us form input:-moz-placeholder, .contact-us form textarea:-moz-placeholder { color: #ccc; }
.contact-us form input:-ms-input-placeholder, .contact-us form textarea:-ms-input-placeholder { color: #ccc; }
.contact-us form input::-webkit-input-placeholder, .contact-us form textarea::-webkit-input-placeholder { color: #ccc; }

/* ----- Google maps ----- */

.map {
    margin: 20px 20px 40px 20px;
    height: 300px;
    border: 5px solid #f8f8f8;
}


/********** ----- SERVICES PAGE ----- **********/

/* ----- Services full width text ----- */

.services-full-width {
    margin-top: 20px;
}

.services-full-width-text {
    padding: 10px 0 0 0;
    text-align: left;
}

.services-full-width-text h4 {
    margin-top: 25px;
    padding: 0 20px;
    font-family: 'Droid Sans', Helvetica, Arial, sans-serif;
    font-size: 16px;
    color: #5d5d5d;
    text-transform: uppercase;
    text-shadow: 0 1px 0 rgba(255,255,255,.7);
}

.services-full-width-text p {
    padding: 0 20px;
    line-height: 28px;
    font-size: 13px;
}

/* ----- Services half width text ----- */

.services-half-width {
    margin-top: 20px;
}

.services-half-width-text {
    padding: 10px 0;
    text-align: left;
}

.services-half-width-text h4 {
    margin-top: 25px;
    padding: 0 20px;
    font-family: 'Droid Sans', Helvetica, Arial, sans-serif;
    font-size: 16px;
    color: #5d5d5d;
    text-transform: uppercase;
    text-shadow: 0 1px 0 rgba(255,255,255,.7);
}

.services-half-width-text p {
    padding: 0 20px;
    line-height: 28px;
    font-size: 13px;
}

/* ----- Call to action ----- */

.call-to-action {
    margin-top: 20px;
    padding-bottom: 50px;
}

.call-to-action-text {
    padding: 25px 0 20px 0;
    text-align: left;
    background: #f8f8f8;
    overflow: hidden;
}

.call-to-action-text:hover {
    box-shadow:
        0 3px 10px 0 rgba(0,0,0,.05),
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -o-transition: all .5s;
    -moz-transition: all .5s;
    -webkit-transition: all .5s;
    -ms-transition: all .5s;
}

.call-to-action-text .ca-text, .call-to-action-text .ca-button {
    float: left;
    padding: 0 0 0 35px;
    line-height: 30px;
    font-size: 18px;
    font-style: italic;
}

.call-to-action-text .ca-button {
    float: right;
    padding: 0 35px 0 0;
}

.call-to-action-text .ca-button a {
    padding: 5px 22px;
    background: #268667;
    color: #c1c1c1;
    text-decoration: none;
    -moz-box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
}

.call-to-action-text .ca-button a:hover {
    -moz-box-shadow: none;
    -webkit-box-shadow: none;
    box-shadow: none;
}

.call-to-action-text .ca-button a:active {
    -moz-box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 5px 10px 0 rgba(0,0,0,.15) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
}


/********** ----- PORTFOLIO PAGE ----- **********/

.portfolio-page {
    margin-top: 20px;
    padding-bottom: 50px;
}

.portfolio-page h4.filter-portfolio {
    margin-top: 35px;
    padding: 0 20px;
    font-family: 'Droid Sans', Helvetica, Arial, sans-serif;
    font-size: 16px;
    color: #5d5d5d;
    text-align: left;
    text-transform: uppercase;
    text-shadow: 0 1px 0 rgba(255,255,255,.7);
}

ul.portfolio-img {
    margin: 0;
    overflow: hidden;
}

ul.portfolio-img li {
    list-style: none;
}

.filter-portfolio a { color: #5d5d5d; text-decoration: none; }
.filter-portfolio a:hover { color: #268667; text-decoration: none; }

.filter-portfolio a#active-imgs { color: #268667; }
.filter-portfolio a#active-imgs:hover { color: #5d5d5d; }




/* ----- Media queries ----- */

@media (min-width: 980px) and (max-width: 1200px) {

    .flex-caption {
        max-width: 700px;
    }

    .what-we-do .service p, .portfolio .work p {
        padding-left: 10px;
        padding-right: 10px;
    }

    .call-to-action-text {
        padding-bottom: 30px;
    }

    .call-to-action-text .ca-text {
        padding: 0 35px;
    }

    .call-to-action-text .ca-button {
        margin-top: 10px;
    }

}

@media (min-width: 768px) and (max-width: 979px) {

    .header ul.nav li a {
        padding: 15px 20px 15px 20px;
        font-weight: normal;
        text-align: left;
        -moz-border-radius: 0;
        -webkit-border-radius: 0;
        border-radius: 0;
    }

    .header ul.nav li.current-page a {
        padding-top: 15px;
    }

    .header ul.nav li a i, .header ul.nav li a br {
        display: none;
    }

    .flex-caption {
        max-width: 500px;
    }

    .what-we-do .service p, .portfolio .work p {
        padding-left: 10px;
        padding-right: 10px;
    }

    .call-to-action-text {
        padding-bottom: 30px;
    }

    .call-to-action-text .ca-text {
        padding: 0 35px;
    }

    .call-to-action-text .ca-button {
        margin-top: 10px;
    }

}

@media (max-width: 767px) {

    body {
        padding-left: 0;
        padding-right: 0;
    }

    .slider, .what-we-do, .portfolio {
        padding: 0 20px;
    }

    .what-we-do {
        margin-top: 30px;
    }

    .header ul.nav li a {
        padding: 15px 20px 15px 20px;
        font-weight: normal;
        text-align: left;
        -moz-border-radius: 0;
        -webkit-border-radius: 0;
        border-radius: 0;
    }

    .header ul.nav li.current-page a {
        padding-top: 15px;
    }

    .header ul.nav li a i, .header ul.nav li a br {
        display: none;
    }

    .flex-caption {
        display: none;
    }

    .presentation, footer, .testimonials {
        padding-left: 20px;
        padding-right: 20px;
    }

    .presentation p {
        line-height: 30px;
    }

    .what-we-do .service p, .portfolio .work p {
        padding-left: 10px;
        padding-right: 10px;
    }

    .what-we-do .service .icon-awesome {
        padding-top: 10px;
    }

    .page-title p {
        display: block;
        margin-top: 10px;
        margin-left: 20px;
    }

    .call-to-action-text {
        padding-bottom: 30px;
    }

    .call-to-action-text .ca-text {
        padding: 0 35px;
    }

    .call-to-action-text .ca-button {
        margin-top: 10px;
    }

    .portfolio-page {
        padding-bottom: 50px;
    }

    .tweet_list {
        height: 6em;
    }

}

@media (max-width: 480px) {

    .tweet_list {
        height: 10em;
    }

}
</style>

<body>
<!-- 登入功能串接 ，將VOmemID指定給 memID-->
    <!-- 先取出VO -->
    <%
// 		DriverVO driverVO = (DriverVO) request.getAttribute("driverVO");
    	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
    	String memID=memberVO.getMemID();
    	session.setAttribute("memID",memID);
    MemberService xxx = new MemberService();
    List<MemberVO> yyy =xxx.getAll();
    DriverService driSrc = new DriverService();
    DriverVO driverVO  = driSrc.getOneDriverBymemID(memberVO.getMemID());
    session.setAttribute("driverVO",driverVO);
    	DriverVO drixx = (DriverVO)session.getAttribute("driverVO");
    	System.out.println(yyy);
    	System.out.println(memberVO.getMemID());
    	System.out.println(memberVO.getPic());////
	%>
    <section id="contact">
        <div class="container wow fadeInUp">
            <div class="section-header">
                <h3 class="section-title">司機資料管理(已司機)</h3>
                <p class="section-description">請查看與修改資料</p>
            </div>
        </div>
        <div class="container wow fadeInUp">
            <div class="row justify-content-center">
                <div class="col-lg-1 col-md-4">
                    <div class="info"></div>
                </div>
                <div class="col-lg-9 col-md-8">
                    <div class="form">
<!--                         <div id="sendmessage">Welcome to PICAR. Thank you!</div> -->
                        <div id="errormessage"></div>
                        <form method="post" action="<%=request.getContextPath()%>/driver/driver.do" role="form" class="contactForm" style="margin-bottom: 0px;">
                            <div align="center">個人資料</div><br>
                            <div class="input-group mb-3 ">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">會員編號</span>
                                </div>
                                <input type="text" class="form-control" placeholder="MEMID" value="<%=driverVO.getMemID() %>" aria-label="Username" aria-describedby="basic-addon1" disabled="disabled">
                            </div>
                            <div class="input-group mb-3 ">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">司機編號</span>
                                </div>
                                <input type="text" class="form-control" placeholder="DRIVER1" value="${driverVO.driverID}" aria-label="Username" aria-describedby="basic-addon1" readonly>
                            </div>																			
                            <div class="input-group mb-3 ">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">名字</span>
                                </div>
                                <input type="text" class="form-control" placeholder="DRIVER1" value="${memberVO.name}" aria-label="Username" aria-describedby="basic-addon1" readonly>
                            </div>																			
                            <div class="input-group mb-3 ">
                                <div class="input-group-prepend ">
                                    <span class="input-group-text " id="basic-addon1">車牌號碼</span>
                                </div>
                                <input type="text" class="form-control" placeholder="RAS-9958" value="${driverVO.plateNum}" aria-label="Username" aria-describedby="basic-addon1" readonly>
                            </div>
                                <div class="form-group">
                                    <p>駕照</p>
                                    <c:set var="licence" value="${driverVO.licence}" />
                                    <%
						      byte[] licence = (byte[])pageContext.getAttribute("licence");
						      String encodeImg1 = null;
						      if(licence!=null){
						    	  encodeImg1 = Base64.encode(licence);%>
                                    <img src="data:image/jpg;base64,<%=encodeImg1 %>" id="img1" width='200' height="200" onerror="this.src='cat.jpg'">
                                    <% }%>
                                    </div>
                                    <%-- 						      <td>${driverVO.criminal}</td> --%>
                                    <div class="form-group">
                                        <p>良民證</p>
                                        <c:set var="criminal" value="${driverVO.criminal}" />
                                        <%
						      byte[] criminal = (byte[])pageContext.getAttribute("criminal");
						      String encodeImg2 = null;
						      if(criminal!=null){
						    	  encodeImg2 = Base64.encode(criminal);%>
                                        <img src="data:image/jpg;base64,<%=encodeImg2 %>" id="img2" width='200' height="200" onerror="this.src='cat.jpg'">
                                        <% }%>
                                    </div>
                                    <%-- 						      <td>${driverVO.trafficRecord}</td> --%>
                                    <div class="form-group">
                                        <p>肇事紀錄</p>
                                        <c:set var="trafficRecord" value="${driverVO.trafficRecord}" />
                                        <%
						      byte[] trafficRecord = (byte[])pageContext.getAttribute("trafficRecord");
						      String encodeImg3 = null;
						      if(trafficRecord!=null){
						    	  encodeImg3 = Base64.encode(trafficRecord);%>
                                        <img src="data:image/jpg;base64,<%=encodeImg3 %>" id="img3" width='200' height="200" onerror="this.src='cat.jpg'">
                                        <% }%>
                                    </div>
                                    <%-- 						      <td>${driverVO.idNum}</td> --%>
                                    <div class="form-group">
                                        <p>身分證</p>
                                        <c:set var="idNum" value="${driverVO.idNum}" />
                                        <%
						      byte[] idNum = (byte[])pageContext.getAttribute("idNum");
						      String encodeImg4 = null;
						      if(idNum!=null){
						    	  encodeImg4 = Base64.encode(idNum);%>
                                        <img src="data:image/jpg;base64,<%=encodeImg4 %>" id="img4" width='200' height="200" onerror="this.src='cat.jpg'">
                                        <% }%>
                                    </div>
							<div class="form-group">
                      <p>會員照片</p>
							<img src="<%=request.getServletContext().getContextPath()%>/front-end/member/member.do?memID=${memberVO.memID}"  width='200' height="200"
		          onerror="this.src='cat.jpg'">
                    </div>	
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">評價分數</span>
                                        </div>
                                        <input type="text" class="form-control" placeholder="3" value="${driverVO.score}" aria-label="Username" aria-describedby="basic-addon1" readonly>
                                    </div>
                                    <div align="center">喜好設定</div><br>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">願意共乘載客</span>
                                        </div>
                                        <div class="form-control" aria-label="Username" aria-describedby="basic-addon1"><font color="red">
											<c:if test="${driverVO.sharedCar == 0}">不接受共乘</c:if>
						      				<c:if test="${driverVO.sharedCar == 1}">接受共乘</c:if></font></div>	
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">可載寵物</span>
                                        </div>
                                        <div class="form-control" aria-label="Username" aria-describedby="basic-addon1"><font color="red">
                                        <c:if test="${driverVO.pet == 0}">不要寵物</c:if>
						    			 <c:if test="${driverVO.pet == 1}">寵物我可以</c:if></font>
						    			 </div>	
                                    </div>
								<div class="input-group mb-3">
                                       	 <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">是否抽菸</span>
                                        </div>
                                        <div class="form-control" aria-label="Username" aria-describedby="basic-addon1"><font color="red">
						      <c:if test="${driverVO.smoke == 0}">不接受抽菸</c:if>
						      <c:if test="${driverVO.smoke == 1}">接受抽菸</c:if></font>
						    			 </div>	
						           </div>
						      	<div class="input-group mb-3">
                                       	 <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">提供嬰兒座椅</span>
                                        </div>
                              <div class="form-control" aria-label="Username" aria-describedby="basic-addon1"><font color="red">
						      <c:if test="${driverVO.babySeat == 0}">不提供嬰兒座椅</c:if>
						      <c:if test="${driverVO.babySeat == 1}">提供嬰兒座椅</c:if></font>
						      </div>
						      </div>
						      <div>
                                        <a href="<%=request.getContextPath()%>/front-end/driver/setting.jsp"><i class="fas fa-coins"></i><br />喜好設定</a>
						      </div>
						      
<!--                                     <div align="center">違規狀態</div><br> -->
<!--                                     <div class="input-group mb-3"> -->
<!--                                         <div class="input-group-prepend"> -->
<!--                                             <span class="input-group-text" id="basic-addon1">接單狀態碼:</span> -->
<!--                                         </div> -->
<!--                                         <input type="text" class="form-control" placeholder="1" -->
<!--                                         aria-label="Username" aria-describedby="basic-addon1" readonly> -->
<!-- <!--                                         <DIV> --> 
<!-- <!--                                         <font color="black"> --> 
<%-- <%--                                         <c:if test="${driverVO.banned == 0}">表現不錯，沒被BAN</c:if> --%> 
<%-- <%-- 						      			<c:if test="${driverVO.banned == 1}">BAN</c:if> --%> 
<!-- <!--                                         </font> --> 
<!-- <!-- 						      			</DIV> --> 
                                    </div>
<!--                                     <div class="input-group mb-3"> -->
<!--                                         <div class="input-group-prepend"> -->
<!--                                             <span class="input-group-text" id="basic-addon1">到期日:</span> -->
<!--                                         </div> -->
<!--                                         <font color="black"> -->
<%--                                             <c:out value="${driverVO.deadline}" default="表現不錯，繼續開車" /> --%>
<!--                                         </font> -->
<!--                                     </div> -->
<!--                                     <div class="text-center"> -->
<!--                                         <button type="submit" class="btn btn-block ">確認修改</button> -->
<%--                                         	<input type="hidden" name="msgID"  value="${brodVO.msgID}"> --%>
<!--                                         <input type="hidden" name="action" value="UPDATE_DRI" /> -->
<!--                                     </div> -->
                                    <!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
                                    <div class="text-center" class="btn btn-block" class="btn btn-outline-success">
						           <a href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/index.jsp">返回司機首頁
                                    </button>
                                    </div>
                                    <div class="text-center" class="btn btn-block" class="btn btn-outline-success">
						           <a href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/index.jsp">返回會員首頁
                                    </button>
                                    </div>
                                    
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- #contact -->
    <!--==========================
    Footer
  ============================-->
    <footer id="footer">
        <div class="footer-top">
            <div class="container"></div>
        </div>
        <div class="container">
            <div class="copyright">
                &copy; Copyright <strong>Regna</strong>. All Rights Reserved
            </div>
            <div class="credits">
                <!--
          All the links in the footer should remain intact.
          You can delete the links only if you purchased the pro version.
          Licensing information: https://bootstrapmade.com/license/
          Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Regna
        -->
                Bootstrap Templates by <a href="https://bootstrapmade.com/">BootstrapMade</a>
            </div>
        </div>
    </footer>
    <!-- #footer -->
    <jsp:include page="/regna-master/body.jsp" />
</body>

</html>