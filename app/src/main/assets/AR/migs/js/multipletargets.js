var World = {
	loaded: false,
    video_RECURSO10: null,
    interactionContainer: 'snapContainer',

	init: function initFn() {
	    AR.context.services.sensors = false;
        this.createOverlays();
	},

	createOverlays: function createOverlaysFn() {
		/*
			First an AR.ClientTracker needs to be created in order to start the recognition engine. It is initialized with a URL specific to the target collection. Optional parameters are passed as object in the last argument. In this case a callback function for the onLoaded trigger is set. Once the tracker is fully loaded the function worldLoaded() is called.

			Important: If you replace the tracker file with your own, make sure to change the target name accordingly.
			Use a specific target name to respond only to a certain target or use a wildcard to respond to any or a certain group of targets.

			Adding multiple targets to a target collection is straightforward. Simply follow our Target Management Tool documentation. Each target in the target collection is identified by its target name. By using this target name, it is possible to create an AR.Trackable2DObject for every target in the target collection.
		*/
		this.tracker = new AR.ClientTracker("assets/tracker.wtc", {
			onLoaded: this.worldLoaded
		});

        this.imgButton = new AR.ImageResource("assets/masinfo.png");
		this.videoButton = new AR.ImageResource("assets/vervideo.png");

		var imgAibo = new AR.ImageResource("assets/imageAibo.png");
        var imgFriden = new AR.ImageResource("assets/fridenOverlay.png");
        var imgSantesmases = new AR.ImageResource("assets/santesmasesOverlay.png");
        var aibooverlay = new AR.ImageDrawable(imgAibo, 0.5, {
			offsetX: 0.01,
			offsetY: 0.05
		});
		var fridenoverlay = new AR.ImageDrawable(imgFriden, 0.5, {
			offsetX: 0.01,
			offsetY: 0.05
		});
		var santesmasesoverlay = new AR.ImageDrawable(imgSantesmases, 0.5, {
			offsetX: 0.12,
			offsetY: -0.01
        });
		var santesmasesinfo = this.createWwwButton("http://www.scie.es/historia-progreso-informatica-cientifica-universitaria-espana/legado-jose-garcia-santesmases-1952-a-1977/", 0.15, {
			offsetX: 0,
			offsetY: -0.25,
			zOrder: 1
		});
		var fridenvideo = this.createVideoButton("https://www.youtube.com/watch?v=w76pNBqTmNk", 0.15, {
			offsetX: 0,
			offsetY: -0.25,
			zOrder: 1
		});
		var aibovideo = this.createVideoButton("https://www.youtube.com/watch?v=2l2P8Uz0LkA", 0.15, {
			offsetX: 0,
			offsetY: -0.25,
			zOrder: 1
		});
		/*
			The AR.Trackable2DObject for the second page uses the same tracker but with a different target name and the second overlay.
		*/
		var augment1 = new AR.Trackable2DObject(this.tracker, "pattern1", {
        			drawables: {
        				cam: [santesmasesinfo, santesmasesoverlay]
        			}
        });
        this.AugmentReality_RECURSO10 = new AR.Trackable2DObject( this.tracker, "trianglepattern2", {
            onEnterFieldOfVision: function onEnterFieldOfVisionFn() {
                if ( !World.video_RECURSO10 )
                {
	                // you could pass the name of the video depending of the trackable
	                World.createVideoDrawable();
                }

                World.video_RECURSO10.play( -1 );
            },
            onExitFieldOfVision: function onExitFieldOfVisionFn() {
                if ( World.video_RECURSO10 )
                {
	                World.video_RECURSO10.stop();
                }
            }
        } );
		var augment3 = new AR.Trackable2DObject(this.tracker, "marker3", {
			drawables: {
				cam: [fridenvideo, fridenoverlay]
			}
		});
		var augment3 = new AR.Trackable2DObject(this.tracker, "a3", {
			drawables: {
				cam: [fridenvideo, fridenoverlay]
			}
		});
		var augment3 = new AR.Trackable2DObject(this.tracker, "bssm4", {
			drawables: {
				cam: [aibovideo, aibooverlay]
			}
		});

	},
	createWwwButton: function createWwwButtonFn(url, size, options) {
		/*
			As the button should be clickable the onClick trigger is defined in the options passed to the AR.ImageDrawable. In general each drawable can be made clickable by defining its onClick trigger. The function assigned to the click trigger calls AR.context.openInBrowser with the specified URL, which opens the URL in the browser.
		*/
		options.onClick = function() {
			AR.context.openInBrowser(url);
		};
		return new AR.ImageDrawable(this.imgButton, size, options);
	},	createVideoButton: function createWwwButtonFn(url, size, options) {
		/*
			As the button should be clickable the onClick trigger is defined in the options passed to the AR.ImageDrawable. In general each drawable can be made clickable by defining its onClick trigger. The function assigned to the click trigger calls AR.context.openInBrowser with the specified URL, which opens the URL in the browser.
		*/
		options.onClick = function() {
			AR.context.openInBrowser(url);
		};
		return new AR.ImageDrawable(this.videoButton, size, options);
	},
    createVideoDrawable: function(shouldStartImmediately) {
		World.video_RECURSO10 = new AR.VideoDrawable( "assets/video.mp4", 1, {
			offsetY: 0,
			offsetX: 0,
			onError: function onErrorFn() {
				alert( "Error fire" );
			},
			onLoaded: function videoLoaded() {
				alert( "OnLoaded fire" );
			}
		  } );

		  World.AugmentReality_RECURSO10.drawables.addCamDrawable(World.video_RECURSO10);
    },
	worldLoaded: function worldLoadedFn() {
		var cssDivInstructions = " style='display: table-cell;vertical-align: middle; text-align: right; width: 50%; padding-right: 15px;'";
		var cssDivSurfer = " style='display: table-cell;vertical-align: middle; text-align: left; padding-right: 15px; width: 38px'";
		var cssDivBiker = " style='display: table-cell;vertical-align: middle; text-align: left; padding-right: 15px;'";
		document.getElementById('loadingMessage').innerHTML =
			"<div" + cssDivInstructions + ">Scan Targets:</div>" +
			"<div" + cssDivSurfer + "><img src='assets/marker1.png'></img></div>" +
            "<div" + cssDivSurfer + "><img src='assets/marker2.png'></img></div>" +
            "<div" + cssDivSurfer + "><img src='assets/marker3.png'></img></div>" +
            "<div" + cssDivBiker + "><img src='assets/marker4.png'></img></div>";

		// Remove Scan target message after 10 sec.
		setTimeout(function() {
			var e = document.getElementById('loadingMessage');
			e.parentElement.removeChild(e);
		}, 10000);
	}
};

World.init();
