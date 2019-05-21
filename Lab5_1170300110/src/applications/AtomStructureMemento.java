package applications;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import physicalObject.Electron;
import track.Track;

public class AtomStructureMemento {
		private int numTracks;
		// track 到 物体的关系映射
		private Map<Track, ArrayList<Electron>> rel = new HashMap<Track, ArrayList<Electron>>();
		// 物体
		private List<Electron> objects = new ArrayList<Electron>();
		// trakcs
		private List<Track> tracks = new ArrayList<Track>();
		
		
		public AtomStructureMemento(Map<Track, ArrayList<Electron>> rel, List<Electron> objects,
				List<Track> tracks, int numTracks) throws ClassNotFoundException, IOException {
			this.rel = deepCopyMap(rel);
			this.objects = deepCopyObjectList(objects);
			this.tracks = deepCopyTrackList(tracks);
			this.setNumTracks(numTracks);
			
		}
		
		public List<Track> getTracks() throws ClassNotFoundException, IOException {
			return deepCopyTrackList(this.tracks);
		}

		public void setTracks(List<Track> tracks) throws ClassNotFoundException, IOException {
			this.tracks = deepCopyTrackList(tracks);
		}

		public Map<Track, ArrayList<Electron>> getRel() throws ClassNotFoundException, IOException {
			return deepCopyMap(this.rel);
		}
		public void setRel(Map<Track, ArrayList<Electron>> rel) throws ClassNotFoundException, IOException {
			this.rel = deepCopyMap(rel);
		}
		public List<Electron> getObjects() throws ClassNotFoundException, IOException {
			
			return deepCopyObjectList(this.objects);
		}
		public void setObjects(List<Electron> objects) throws ClassNotFoundException, IOException {
			
			this.objects = deepCopyObjectList(objects);
		}
		
		private List<Track> deepCopyTrackList(List<Track> src) throws IOException, ClassNotFoundException {  
		    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
		    ObjectOutputStream out = new ObjectOutputStream(byteOut);  
		    out.writeObject(src);

		    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
		    ObjectInputStream in = new ObjectInputStream(byteIn);  
		    
		    @SuppressWarnings("unchecked")  
		    List<Track> dest = (List<Track>) in.readObject();  
		    return dest;  
		}  
		
		private List<Electron> deepCopyObjectList(List<Electron> src) throws IOException, ClassNotFoundException {  
		    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
		    ObjectOutputStream out = new ObjectOutputStream(byteOut);  
		    out.writeObject(src);

		    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
		    ObjectInputStream in = new ObjectInputStream(byteIn);  
		    
		    @SuppressWarnings("unchecked")  
		    List<Electron> dest = (List<Electron>) in.readObject();  
		    return dest;  
		}  
		
		private Map<Track, ArrayList<Electron>> deepCopyMap(Map<Track, ArrayList<Electron>> src) throws IOException, ClassNotFoundException {  
		    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
		    ObjectOutputStream out = new ObjectOutputStream(byteOut);  
		    out.writeObject(src);

		    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
		    ObjectInputStream in = new ObjectInputStream(byteIn);  
		    
		    @SuppressWarnings("unchecked")  
		    Map<Track, ArrayList<Electron>> dest = (Map<Track, ArrayList<Electron>>) in.readObject();  
		    return dest;  
		}

		public int getNumTracks() {
			return numTracks;
		}

		public void setNumTracks(int numTracks) {
			this.numTracks = numTracks;
		}

}
