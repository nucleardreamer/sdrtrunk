package io.github.dsheirer.module.decode.p25.message.ldu.lc;

import io.github.dsheirer.module.decode.p25.message.ldu.LDU1Message;
import io.github.dsheirer.module.decode.p25.reference.LinkControlOpcode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnitToUnitVoiceChannelUser extends LDU1Message
{
	private final static Logger mLog = 
			LoggerFactory.getLogger( UnitToUnitVoiceChannelUser.class );
    /* Service Options */
    public static final int EMERGENCY_FLAG = 376;
    public static final int ENCRYPTED_CHANNEL_FLAG = 377;
    public static final int DUPLEX_MODE = 382;
    public static final int SESSION_MODE = 383;
	public static final int[] TARGET_ADDRESS = { 536,537,538,539,540,541,546,
		547,548,549,550,551,556,557,558,559,560,561,566,567,568,569,570,571 };
	public static final int[] SOURCE_ADDRESS = { 720,721,722,723,724,725,730,
		731,732,733,734,735,740,741,742,743,744,745,750,751,752,753,754,755 };

	public UnitToUnitVoiceChannelUser( LDU1Message message )
	{
		super( message );
	}
	
    @Override
    public String getEventType()
    {
        return LinkControlOpcode.UNIT_TO_UNIT_VOICE_CHANNEL_USER.getDescription();
    }

	@Override
	public String getMessage()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append( getMessageStub() );
		
		if( isEmergency() )
		{
			sb.append( " EMERGENCY" );
		}
		if( isEncryptedChannel() )
		{
			sb.append( " ENCRYPTED CHANNEL" );
		}

		sb.append( " FROM:" + getSourceAddress() );
		
		sb.append( " TO:" + getTargetAddress() );
		
		return sb.toString();
	}

    public boolean isEmergency()
    {
        return mMessage.get( EMERGENCY_FLAG );
    }
    
    public boolean isEncryptedChannel()
    {
        return mMessage.get( ENCRYPTED_CHANNEL_FLAG );
    }
    
    public DuplexMode getDuplexMode()
    {
        return mMessage.get( DUPLEX_MODE ) ? DuplexMode.FULL : DuplexMode.HALF;
    }

    public SessionMode getSessionMode()
    {
        return mMessage.get( SESSION_MODE ) ? 
                SessionMode.CIRCUIT : SessionMode.PACKET;
    }
    
    public String getTargetAddress()
    {
    	return mMessage.getHex( TARGET_ADDRESS, 6 );
    }
    
    public String getSourceAddress()
    {
    	return mMessage.getHex( SOURCE_ADDRESS, 6 );
    }
}
