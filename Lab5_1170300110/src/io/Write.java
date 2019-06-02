package io;

import applications.AtomStructure;
import applications.SocialNetworkCircleBigger;
import applications.TrackGame;

public interface Write {
  public void writeFileWriter1(TrackGame system);
  public void writeBufferWriter1(TrackGame system);
  public void writeStream1(TrackGame system);

  public void writeFileWriter2(AtomStructure system);
  public void writeBufferWriter2(AtomStructure system);
  public void writeStream2(AtomStructure system);

  public void writeFileWriter3(SocialNetworkCircleBigger system);
  public void writeBufferWriter3(SocialNetworkCircleBigger system);
  public void writeStream3(SocialNetworkCircleBigger system);
}
