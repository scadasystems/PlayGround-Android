var arrayVideo = new Array();
// video route -> https://storage.googleapis.com/lulzm/assets/first_video.mp4
arrayVideo[0] = 'https://storage.googleapis.com/lulzm/assets/first_video.mp4';
arrayVideo[1] = 'https://storage.googleapis.com/lulzm/assets/second_video.mp4';
arrayVideo[2] = 'https://storage.googleapis.com/lulzm/assets/third_video.mp4';
arrayVideo[3] = 'https://storage.googleapis.com/lulzm/assets/fourth_video.mp4';

function ratio11() {
    for (var i = 0; i < 2; i++) {
        var video = document.createElement('video');

        video.className = 'video';
        video.setAttribute("width", "50%");
        video.setAttribute("height", "158px");
        video.setAttribute("src", arrayVideo[i]);
        video.setAttribute("autoplay", "autoplay");
        video.setAttribute("loop", "loop");
        video.setAttribute("type", "video/mp4");
        video.style.objectFit = 'cover';
        video.style.boxSizing = 'border-box';
        video.style.float = 'left';

        document.getElementById('video_container').appendChild(video);
    }
}

function ratio13() {
    for (var i = 0; i < 2; i++) {
        var video = document.createElement('VIDEO');

        video.className = 'video';
        if (i == 0) {
            video.setAttribute("width", "25%");
        } else {
            video.setAttribute("width", "75%");
        }
        video.setAttribute("height", "158px");
        video.setAttribute("src", arrayVideo[i]);
        video.setAttribute("autoplay", "autoplay");
        video.setAttribute("loop", "loop");
        video.setAttribute("type", "video/mp4");
        video.style.objectFit = 'cover';
        video.style.boxSizing = 'border-box';
        video.style.float = 'left';

        document.getElementById('video_container').appendChild(video);
    }
}

function ratio31() {
    for (var i = 0; i < 2; i++) {
        var video = document.createElement('VIDEO');

        video.className = 'video';
        if (i == 1) {
            video.setAttribute("width", "25%");
        } else {
            video.setAttribute("width", "75%");
        }
        video.setAttribute("height", "158px");
        video.setAttribute("src", arrayVideo[i]);
        video.setAttribute("autoplay", "autoplay");
        video.setAttribute("loop", "loop");
        video.setAttribute("type", "video/mp4");
        video.style.objectFit = 'cover';
        video.style.boxSizing = 'border-box';
        video.style.float = 'left';

        document.getElementById('video_container').appendChild(video);
    }
}

function ratio1111() {
    for (var i = 0; i < 4; i++) {
        var video = document.createElement('VIDEO');

        video.className = 'video';
        video.setAttribute("width", "25%");
        video.setAttribute("height", "158px");
        video.setAttribute("src", arrayVideo[i]);
        video.setAttribute("autoplay", "autoplay");
        video.setAttribute("loop", "loop");
        video.setAttribute("type", "video/mp4");
        video.style.objectFit = 'cover';
        video.style.boxSizing = 'border-box';
        video.style.float = 'left';

        document.getElementById('video_container').appendChild(video);
    }
}
