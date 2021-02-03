// function YoutubePlayer(vidID) {
//     this.vidID = vidID;
//
//     this.runScript = function () {
//         // 2. This code loads the IFrame Player API code asynchronously.
//         var tag = document.createElement('script');
//
//         debugger
//         tag.src = "https://www.youtube.com/iframe_api";
//         var firstScriptTag = document.getElementsByTagName('script')[0];
//         firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
//
// // 3. This function creates an <iframe> (and YouTube player)
// //    after the API code downloads.
//         var player;
//
//         function onYouTubeIframeAPIReady() {
//             player = new YT.Player('player_youtube', {
//                 height: '540',
//                 width: '960',
//                 videoId: 'M7lc1UVf-VE',
//                 events: {
//                     'onReady': onPlayerReady,
//                     'onStateChange': onPlayerStateChange
//                 }
//             });
//         }
//
// // 4. The API will call this function when the video player is ready.
//         function onPlayerReady(event) {
//             event.target.playVideo();
//         }
//
// // 5. The API calls this function when the player's state changes.
// //    The function indicates that when playing a video (state=1),
// //    the player should play for six seconds and then stop.
//         var done = false;
//
//         function onPlayerStateChange(event) {
//             if (event.data == YT.PlayerState.PLAYING && !done) {
//                 done = true;
//             }
//         }
//
//         function stopVideo() {
//             player.stopVideo();
//         }
//     }
// }

// function runScript() {
// 2. This code loads the IFrame Player API code asynchronously.
    var tag = document.createElement('script');

    tag.src = "https://www.youtube.com/iframe_api";
    var firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    var elemForPlayer = document.getElementById('player_youtube');

// 3. This function creates an <iframe> (and YouTube player)
//    after the API code downloads.
    var player;

    function onYouTubeIframeAPIReady() {
        player = new YT.Player('player_youtube', {
            height: '540',
            width: '960',
            videoId: elemForPlayer.title,
            events: {
                'onReady': onPlayerReady,
                'onStateChange': onPlayerStateChange
            }
        });
    }

// 4. The API will call this function when the video player is ready.
    function onPlayerReady(event) {
        event.target.playVideo();
    }

// 5. The API calls this function when the player's state changes.
//    The function indicates that when playing a video (state=1),
//    the player should play for six seconds and then stop.
    var done = false;

    function onPlayerStateChange(event) {
        if (event.data == YT.PlayerState.PLAYING && !done) {
            done = true;
        }
    }

    function stopVideo() {
        player.stopVideo();
    }
    // function loadVideoById(){
    //     player.loadVideoById("bHQqvYy5KYo", 5, "large")
    // }
// }