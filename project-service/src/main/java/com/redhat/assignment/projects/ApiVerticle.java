package com.redhat.assignment.projects;

import java.util.List;

import com.redhat.assignment.projects.model.Project;
import com.redhat.assignment.projects.service.ProjectService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class ApiVerticle extends AbstractVerticle {

    private ProjectService projectService;

    public ApiVerticle(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        Router router = Router.router(vertx);
        router.get("/projects").handler(this::getProjects);
        router.get("/project/:projectId").handler(this::getProject);
        router.route("/projects/status/:status").handler(this::getProjectStatus);
       

        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(config().getInteger("projects.http.port", 8080), result -> {
                if (result.succeeded()) {
                    startFuture.complete();
                } else {
                    startFuture.fail(result.cause());
                }
            });
    }

    private void getProjects(RoutingContext rc) {
    	projectService.getProjects(ar -> {
            if (ar.succeeded()) {
                List<Project> projects = ar.result();
                JsonArray json = new JsonArray();
                projects.stream()
                    .map(p -> p.toJson())
                    .forEach(p -> json.add(p));
                rc.response()
                    .putHeader("Content-type", "application/json")
                    .end(json.encodePrettily());
            } else {
                rc.fail(ar.cause());
            }
        });
    }

    private void getProject(RoutingContext rc) {
        String projectId = rc.request().getParam("projectId");
        projectService.getProject(projectId, ar -> {
            if (ar.succeeded()) {
                Project project = ar.result();
                JsonObject json;
                if (project != null) {
                    json = project.toJson();
                    rc.response()
                        .putHeader("Content-type", "application/json")
                        .end(json.encodePrettily());
                } else {
                    rc.fail(404);
                }
            } else {
                rc.fail(ar.cause());
            }
        });
    }

    private void getProjectStatus(RoutingContext rc) {
        String status = rc.request().getParam("status");
        projectService.getProjectStatus(status, ar -> {
            if (ar.succeeded()) {
                List<Project> projects = ar.result();
                
                JsonArray json = new JsonArray();
                projects.stream()
                    .map(p -> p.toJson())
                    .forEach(p -> json.add(p));
                rc.response()
                    .putHeader("Content-type", "application/json")
                    .end(json.encodePrettily());
            } else {
                rc.fail(ar.cause());
            }
        });
    }

//    private void health(Future<Status> future) {
//        catalogService.ping(ar -> {
//            if (ar.succeeded()) {
//                // HealthCheckHandler has a timeout of 1000s. If timeout is exceeded, the future will be failed
//                if (!future.isComplete()) {
//                    future.complete(Status.OK());
//                }
//            } else {
//                if (!future.isComplete()) {
//                    future.complete(Status.KO());
//                }
//            }
//        });
//    }

}
