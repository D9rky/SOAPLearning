package com.in28minutes.soap.webservices.coursemanagement.soap;

import com.in28minutes.courses.CourseDetailsType;
import com.in28minutes.courses.GetCourseDetailsRequestType;
import com.in28minutes.courses.GetCourseDetailsResponseType;
import com.in28minutes.soap.webservices.coursemanagement.soap.bean.Course;
import com.in28minutes.soap.webservices.coursemanagement.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;


    @PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetCourseDetailsRequestType")
    @ResponsePayload
    public GetCourseDetailsResponseType processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequestType request){


        Course course = service.findById(request.getId());

        return mapCourse(course);
    }

    private GetCourseDetailsResponseType mapCourse(Course course) {
        GetCourseDetailsResponseType response = new GetCourseDetailsResponseType();

        CourseDetailsType courseDetails = new CourseDetailsType();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());

        response.setCourseDetails(courseDetails);
        return response;
    }
}
