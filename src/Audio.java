import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Audio {
	public Audio(String nomeDoAudio){
		URL url = Audio.class.getResource(nomeDoAudio);
		AudioClip audio = Applet.newAudioClip(url);
		audio.play();
	}
}
