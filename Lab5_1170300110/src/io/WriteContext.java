package io;

import applications.AtomStructure;
import applications.SocialNetworkCircleBigger;
import applications.TrackGame;

public class WriteContext {
  Write writeStrategy = new ConcreteWrite();

  public void writeFileWriter1(TrackGame system){
    writeStrategy.writeFileWriter1(system);
  }

  public void writeBufferWriter1(TrackGame system){
    writeStrategy.writeBufferWriter1(system);
  }

  public void writeStream1(TrackGame system){
    writeStrategy.writeStream1(system);
  }

  public void writeFileWriter2(AtomStructure system){
    writeStrategy.writeFileWriter2(system);
  }

  public void writeBufferWriter2(AtomStructure system){
    writeStrategy.writeBufferWriter2(system);
  }

  public void writeStream2(AtomStructure system){
    writeStrategy.writeStream2(system);
  }

  public void writeFileWriter3(SocialNetworkCircleBigger system){
    writeStrategy.writeFileWriter3(system);
  }

  public void writeBufferWriter3(SocialNetworkCircleBigger system){
    writeStrategy.writeBufferWriter3(system);
  }

  public void writeStream3(SocialNetworkCircleBigger system){
    writeStrategy.writeStream3(system);
  }
}
