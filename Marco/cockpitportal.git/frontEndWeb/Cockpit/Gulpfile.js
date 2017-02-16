var gulp = require('gulp');
var minifyHTML = require('gulp-minify-html');
var minifyCss = require('gulp-minify-css');
var minifyJs = require('gulp-uglify');

gulp.task('default', function () {
    'use strict';

    gulp.src('./*.html')
        //    .pipe(minifyHTML({}))
        .pipe(gulp.dest('./dist'));


    gulp.src('./view/*.html')
        //  .pipe(minifyHTML({}))
        .pipe(gulp.dest('./dist/view'));


    gulp.src('./css/*.css')
        //    .pipe(minifyCss({}))
        .pipe(gulp.dest('./dist/css'));

    gulp.src('./js/*.js')
        //    .pipe(minifyJs())
        .pipe(gulp.dest('./dist/js'));

    gulp.src('./img/*.*')
        .pipe(gulp.dest('./dist/img'));

    gulp.src('bower_components/semantic-ui/dist/themes/basic/assets/fonts/*.*')
        .pipe(gulp.dest('./dist/bower_components/semantic-ui/dist/themes/basic/assets/fonts'));

    gulp.src('bower_components/semantic-ui/dist/themes/default/assets/fonts/*.*')
        .pipe(gulp.dest('./dist/bower_components/semantic-ui/dist/themes/default/assets/fonts/'));

    gulp.src('bower_components/semantic-ui/dist/themes/default/assets/images/*.*')
        .pipe(gulp.dest('./dist/bower_components/semantic-ui/dist/themes/default/assets/images/'));


    var libs = [
        {
            folder: "bower_components/semantic-ui/dist/",
            file: "semantic.min.css"
    },
        {
            folder: "bower_components/semantic-ui/dist/",
            file: "semantic.min.js"
    },
        {
            folder: "bower_components/jquery/dist/",
            file: "jquery.min.js"
    },
        {
            folder: "bower_components/angular/",
            file: "angular.min.js"
    },
        {
            folder: "bower_components/angular-route/",
            file: "angular-route.min.js"
    }

  ];

    libs.forEach(function (lib) {

        console.log("copiando libs: " + lib.folder + lib.file);

        gulp.src(lib.folder + lib.file)
            .pipe(gulp.dest('./dist/' + lib.folder));

    });

});