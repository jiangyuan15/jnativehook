/* JNativeHook: Global keyboard and mouse hooking for Java.
 * Copyright (C) 2006-2016 Alexander Barker.  All Rights Received.
 * https://github.com/kwhat/jnativehook/
 *
 * JNativeHook is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JNativeHook is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jnativehook.keyboard;

// Imports.
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeInputEvent;
import java.awt.Toolkit;

/**
 * An event which indicates that a keystroke occurred at global scope.
 * <p>
 *
 * This low-level event is generated by the native system when a key is pressed
 * or released independent of Java component focus. The event is passed to every
 * <code>NativeKeyListener</code> object which has been registered to receive
 * global key events using the {@link GlobalScreen#addNativeKeyListener(NativeKeyListener)}
 * method. Each <code>NativeKeyListener</code> object will receive a
 * <code>NativeKeyEvent</code> when the event occurs.
 * <p>
 *
 * All <code>NativeKeyEvent</code> objects are dependent on the native platform and
 * keyboard layout. <code>NATIVE_KEY_PRESSED</code> and <code>NATIVE_KEY_RELEASED</code>
 * events are generated for every key code received by the native system. The key being pressed
 * or released is indicated by the getKeyCode method, which returns a virtual key code or
 * <code>VC_UNDEFINED</code>.  Please note that {@link #getKeyChar()} for events of type
 * <code>NATIVE_KEY_PRESSED</code> and <code>NATIVE_KEY_RELEASED</code> will always return
 * <code>CHAR_UNDEFINED</code>.
 * <p>
 *
 * <code>NATIVE_KEY_TYPED</code> events are produced for <code>NATIVE_KEY_PRESSED</code>
 * events that produce valid Unicode characters for the current keyboard layout. The
 * {@link #getKeyChar()} method always returns a valid Unicode character for this type of
 * event.  Please note that {@link #getKeyCode()} will always return <code>VC_UNDEFINED</code>
 * for <code>NATIVE_KEY_TYPED</code> events.
 * <p>
 *
 * Virtual key codes only represent the physical key that has been pressed and
 * should not be mistaken with the character mapped to that key by the operating
 * system.  <code>NATIVE_KEY_PRESSED</code> and <code>NATIVE_KEY_RELEASED</code> events
 * should only be used to determin phisical key state, while <code>NATIVE_KEY_TYPED</code>
 * events can be used to determine the Unicode representation of the <code>NativeKeyEvent</code>.
 * <p>
 *
 * @author	Alexander Barker (<a href="mailto:alex@1stleg.com">alex@1stleg.com</a>)
 * @version	2.0
 * @since	1.0
 *
 * @see GlobalScreen
 * @see NativeKeyListener
 */
public class NativeKeyEvent extends NativeInputEvent {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8608981443834617646L;

	/** The raw native key code. */
	private int rawCode;

	/** The virtual key code. */
	private int keyCode;

	/** The Unicode character. */
	private char keyChar;

	/** The virtual key location. */
	private int keyLocation;


	/** The first number in the range of ID's used for native key events. */
	public static final int NATIVE_KEY_FIRST				= 2400;

	/** The last number in the range of ID's used for native key events. */
	public static final int NATIVE_KEY_LAST					= 2402;

	/** The "native key typed" event ID. */
	public static final int NATIVE_KEY_TYPED				= NATIVE_KEY_FIRST;

	/** The "native key pressed" event ID. */
	public static final int NATIVE_KEY_PRESSED				= 1 + NATIVE_KEY_FIRST;

	/** The "native key released" event ID. */
	public static final int NATIVE_KEY_RELEASED				= 2 + NATIVE_KEY_FIRST;

	public static final int KEY_LOCATION_UNKNOWN			= 0;
	public static final int KEY_LOCATION_STANDARD			= 1;
	public static final int KEY_LOCATION_LEFT				= 2;
	public static final int KEY_LOCATION_RIGHT				= 3;
	public static final int KEY_LOCATION_NUMPAD				= 4;


	public static final int VC_ESCAPE						= 0x0001;
			
	/** Constants for the F1 through F24 function keys. */
	public static final int VC_F1							= 0x003B;
	public static final int VC_F2							= 0x003C;
	public static final int VC_F3							= 0x003D;
	public static final int VC_F4							= 0x003E;
	public static final int VC_F5							= 0x003F;
	public static final int VC_F6							= 0x0040;
	public static final int VC_F7							= 0x0041;
	public static final int VC_F8							= 0x0042;
	public static final int VC_F9							= 0x0043;
	public static final int VC_F10							= 0x0044;
	public static final int VC_F11							= 0x0057;
	public static final int VC_F12							= 0x0058;

	public static final int VC_F13							= 0x005B;
	public static final int VC_F14							= 0x005C;
	public static final int VC_F15							= 0x005D;
	public static final int VC_F16							= 0x0063;
	public static final int VC_F17							= 0x0064;
	public static final int VC_F18							= 0x0065;
	public static final int VC_F19							= 0x0066;
	public static final int VC_F20							= 0x0067;
	public static final int VC_F21							= 0x0068;
	public static final int VC_F22							= 0x0069;
	public static final int VC_F23							= 0x006A;
	public static final int VC_F24							= 0x006B;
	
	public static final int VC_BACKQUOTE					= 0x0029;
		
	/** VC_0 thru VC_9 */
	public static final int VC_1							= 0x0002;
	public static final int VC_2							= 0x0003;
	public static final int VC_3							= 0x0004;
	public static final int VC_4							= 0x0005;
	public static final int VC_5							= 0x0006;
	public static final int VC_6							= 0x0007;
	public static final int VC_7							= 0x0008;
	public static final int VC_8							= 0x0009;
	public static final int VC_9							= 0x000A;
	public static final int VC_0							= 0x000B;
	
	public static final int VC_MINUS						= 0x000C;	// '-'
	public static final int VC_EQUALS						= 0x000D;	// '='
	public static final int VC_BACKSPACE					= 0x000E;

	public static final int VC_TAB							= 0x000F;
	public static final int VC_CAPS_LOCK					= 0x003A;
	
	/** VC_A thru VC_Z */
	public static final int VC_A							= 0x001E;
	public static final int VC_B							= 0x0030;
	public static final int VC_C							= 0x002E;
	public static final int VC_D							= 0x0020;
	public static final int VC_E							= 0x0012;
	public static final int VC_F							= 0x0021;
	public static final int VC_G							= 0x0022;
	public static final int VC_H							= 0x0023;
	public static final int VC_I							= 0x0017;
	public static final int VC_J							= 0x0024;
	public static final int VC_K							= 0x0025;
	public static final int VC_L							= 0x0026;
	public static final int VC_M							= 0x0032;
	public static final int VC_N							= 0x0031;
	public static final int VC_O							= 0x0018;
	public static final int VC_P							= 0x0019;
	public static final int VC_Q							= 0x0010;
	public static final int VC_R							= 0x0013;
	public static final int VC_S							= 0x001F;
	public static final int VC_T							= 0x0014;
	public static final int VC_U							= 0x0016;
	public static final int VC_V							= 0x002F;
	public static final int VC_W							= 0x0011;
	public static final int VC_X							= 0x002D;
	public static final int VC_Y							= 0x0015;
	public static final int VC_Z							= 0x002C;
	
	public static final int VC_OPEN_BRACKET					= 0x001A;	// '['
	public static final int VC_CLOSE_BRACKET				= 0x001B;	// ']'
	public static final int VC_BACK_SLASH					= 0x002B;	// '\'

	public static final int VC_SEMICOLON					= 0x0027;	// ';'
	public static final int VC_QUOTE						= 0x0028;
	public static final int VC_ENTER						= 0x001C;

	public static final int VC_COMMA						= 0x0033;	// ','
	public static final int VC_PERIOD						= 0x0034;	// '.'
	public static final int VC_SLASH						= 0x0035;	// '/'
	
	public static final int VC_SPACE						= 0x0039;


	public static final int VC_PRINTSCREEN					= 0x0E37;
	public static final int VC_SCROLL_LOCK					= 0x0046;
	public static final int VC_PAUSE						= 0x0E45;


	/** Edit Key Zone */
	public static final int VC_INSERT						= 0x0E52;
	public static final int VC_DELETE						= 0x0E53;
	public static final int VC_HOME							= 0x0E47;
	public static final int VC_END							= 0x0E4F;
	public static final int VC_PAGE_UP						= 0x0E49;
	public static final int VC_PAGE_DOWN					= 0x0E51;


	/** Begin Cursor Key Zone */
	public static final int VC_UP							= 0xE048;
	public static final int VC_LEFT							= 0xE04B;
	public static final int VC_CLEAR						= 0xE04C;
	public static final int VC_RIGHT						= 0xE04D;
	public static final int VC_DOWN							= 0xE050;


	/** Begin Numeric Zone */
	public static final int VC_NUM_LOCK						= 0x0045;
	public static final int VC_SEPARATOR					= 0x0053;


	/**  Modifier and Control Keys */
	public static final int VC_SHIFT						= 0x002A;
	public static final int VC_CONTROL						= 0x001D;
	public static final int VC_ALT							= 0x0038;	// Option or Alt Key
	public static final int VC_META							= 0x0E5B;	// Windows or Command Key
	public static final int VC_CONTEXT_MENU					= 0x0E5D;


	/**  Media and Extra Keys */
	public static final int VC_POWER						= 0xE05E;
	public static final int VC_SLEEP						= 0xE05F;
	public static final int VC_WAKE							= 0xE063;

	public static final int VC_MEDIA_PLAY					= 0xE022;
	public static final int VC_MEDIA_STOP					= 0xE024;
	public static final int VC_MEDIA_PREVIOUS				= 0xE010;
	public static final int VC_MEDIA_NEXT					= 0xE019;
	public static final int VC_MEDIA_SELECT					= 0xE06D;
	public static final int VC_MEDIA_EJECT					= 0xE02C;

	public static final int VC_VOLUME_MUTE					= 0xE020;
	public static final int VC_VOLUME_UP					= 0xE030;
	public static final int VC_VOLUME_DOWN					= 0xE02E;

	public static final int VC_APP_MAIL						= 0xE06C;
	public static final int VC_APP_CALCULATOR				= 0xE021;
	public static final int VC_APP_MUSIC					= 0xE03C;
	public static final int VC_APP_PICTURES					= 0xE064;

	public static final int VC_BROWSER_SEARCH				= 0xE065;
	public static final int VC_BROWSER_HOME					= 0xE032;
	public static final int VC_BROWSER_BACK					= 0xE06A;
	public static final int VC_BROWSER_FORWARD				= 0xE069;
	public static final int VC_BROWSER_STOP					= 0xE068;
	public static final int VC_BROWSER_REFRESH				= 0xE067;
	public static final int VC_BROWSER_FAVORITES			= 0xE066;

	/** Japanese Language Keys */
	public static final int VC_KATAKANA						= 0x0070;
	public static final int VC_UNDERSCORE                   = 0x0073;
	public static final int VC_FURIGANA						= 0x0077;
	public static final int VC_KANJI						= 0x0079;
	public static final int VC_HIRAGANA						= 0x007B;
	public static final int VC_YEN							= 0x007D;

	/** Sun keyboards */
	public static final int VC_SUN_HELP						= 0xFF75;

	public static final int VC_SUN_STOP						= 0xFF78;
	public static final int VC_SUN_PROPS					= 0xFF76;
	public static final int VC_SUN_FRONT					= 0xFF77;
	public static final int VC_SUN_OPEN						= 0xFF74;
	public static final int VC_SUN_FIND						= 0xFF7E;
	public static final int VC_SUN_AGAIN					= 0xFF79;
	public static final int VC_SUN_UNDO						= 0xFF7A;
	public static final int VC_SUN_COPY						= 0xFF7C;
	public static final int VC_SUN_INSERT					= 0xFF7D;
	public static final int VC_SUN_CUT						= 0xFF7B;

	/** This value is used to indicate that the keyCode is unknown. */
	public static final int VC_UNDEFINED					= 0x0000;
	
	/** This is used to indicate that the keyChar is undefined. */
	public static final char CHAR_UNDEFINED					= 0xFFFF;

	/**
	 * Instantiates a new native key event.
	 * <p>
	 * Note that passing in an invalid ID results in unspecified behavior.
	 * @param id an integer that identifies the native event type.
	 * @param modifiers the modifier mask for the native event.
	 * <code>NativeInputEvent</code> _MASK modifiers should be used as they are
	 * not compatible with AWT's <code>InputEvent</code> _DOWN_MASK or the older
	 * _MASK modifiers.
	 * @param rawCode the hardware code associated with the native key in this
	 * event.
	 * @param keyCode the virtual key code generated by this event, or
	 * VC_UNDEFINED (for a key-typed event)
	 * @param keyChar the Unicode character generated by this event, or
	 * CHAR_UNDEFINED (for key-pressed and key-released events which do not map
	 * to a valid Unicode character).
	 * @param keyLocation the location ID of the key generating this event.
	 *
	 * @since 1.1
	 */
	public NativeKeyEvent(int id, int modifiers, int rawCode, int keyCode, char keyChar, int keyLocation) {
		super(GlobalScreen.class, id, modifiers);

		this.rawCode = rawCode;
		this.keyCode = keyCode;
		this.keyChar = keyChar;
		this.keyLocation = keyLocation;

		if (id == NATIVE_KEY_TYPED && (keyChar == CHAR_UNDEFINED || keyCode != VC_UNDEFINED)) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Instantiates a new native key event.
	 * <p>
	 * Note that passing in an invalid ID results in unspecified behavior.
	 * @param id an integer that identifies the native event type.
	 * @param modifiers the modifier mask for the native event.
	 * <code>NativeInputEvent</code> _MASK modifiers should be used as they are
	 * not compatible with AWT's <code>InputEvent</code> _DOWN_MASK or the older
	 * _MASK modifiers.
	 * @param rawCode the hardware code associated with the native key in this
	 * event.
	 * @param keyCode the virtual key code generated by this event, or
	 * VC_UNDEFINED (for a key-typed event)
	 * @param keyChar the Unicode character generated by this event, or
	 * CHAR_UNDEFINED (for key-pressed and key-released events which do not map
	 * to a valid Unicode character).
	 *
	 * @since 1.1
	 */
	public NativeKeyEvent(int id, int modifiers, int rawCode, int keyCode, char keyChar) {
		this(id, modifiers, rawCode, keyCode, keyChar, KEY_LOCATION_UNKNOWN);
	}

	/**
	 * Returns the native code associated with the native key in this event.
	 * This is an arbitrary number between 0 and 255 (inclusive) used by the
	 * operating system to represent a physical key on the keyboard.  This
	 * code does not necessarily represent the native key's scan code or ASCII
	 * representation.  To maintain platform independence, you should not rely
	 * on the consistency of this value from platform to platform.
	 *
	 * @return the native key code for this event.
	 */
	public int getRawCode() {
		return this.rawCode;
	}

	/**
	 * Set the rawCode value in this event. For more information on what this
	 * value represents, please see {@link #getRawCode()}.
	 *
	 * @param rawCode the native key code for this event.
	 */
	public void setRawCode(int rawCode) {
		 this.rawCode = rawCode;
	}

	/**
	 * Returns the keyCode associated with this event.  Note,
	 * this method will always return <code>VC_UNDEFINED</code> for the
	 * <code>NATIVE_KEY_TYPED</code> event types.
	 *
	 * @return the native virtual key code.
	 */
	public int getKeyCode() {
		return this.keyCode;
	}

	/**
	 * Set the keyCode value in this event.
	 *
	 * @param keyCode the native virtual key code.
	 */
	public void setKeyCode(int keyCode) {
		 this.keyCode = keyCode;
	}

	/**
	 * Returns the native Unicode character associated with this event.  Note,
	 * this method will always return <code>CHAR_UNDEFINED</code> for
	 * <code>NATIVE_KEY_PRESSED</code> and <code>NATIVE_KEY_RELEASED</code>
	 * event types.
	 *
	 * @return the Unicode character defined for this key event. If no valid
	 * Unicode character exists for this key event, <code>CHAR_UNDEFINED</code>
	 * is returned.
	 */
	public char getKeyChar() {
		return this.keyChar;
	}

	/**
	 * Set the keyChar value in this event.  For example, the
	 * <code>NATIVE_KEY_TYPED</code> event for Shift + "a" returns the Unicode
	 * value 'A'.
	 *
	 * @param keyChar the keyboard character associated with this event.
	 */
	public void setKeyChar(char keyChar) {
		 this.keyChar = keyChar;
	}

	/**
	 * Returns the location of the virtual key for this event.
	 *
	 * @return the location of the virtual key that was pressed or released.
	 */
	public int getKeyLocation() {
		return this.keyLocation;
	}

	/**
	 * Returns a String describing the keyCode, such as "HOME", "F1" or "A".
	 * These strings can be localized by changing the awt.properties file.
	 *
	 * @param keyCode the native virtual key code generated by this event
	 * @return a string containing a text description for a physical key,
	 * identified by its keyCode.
	 */
	public static String getKeyText(int keyCode) {
		// Lookup text values.
		switch (keyCode) {
			case VC_ESCAPE:
				return Toolkit.getProperty("AWT.escape", "Escape");

			// Begin Function Keys
			case VC_F1:
				return Toolkit.getProperty("AWT.f1", "F1");
			case VC_F2:
				return Toolkit.getProperty("AWT.f2", "F2");
			case VC_F3:
				return Toolkit.getProperty("AWT.f3", "F3");
			case VC_F4:
				return Toolkit.getProperty("AWT.f4", "F4");
			case VC_F5:
				return Toolkit.getProperty("AWT.f5", "F5");
			case VC_F6:
				return Toolkit.getProperty("AWT.f6", "F6");
			case VC_F7:
				return Toolkit.getProperty("AWT.f7", "F7");
			case VC_F8:
				return Toolkit.getProperty("AWT.f8", "F8");
			case VC_F9:
				return Toolkit.getProperty("AWT.f9", "F9");
			case VC_F10:
				return Toolkit.getProperty("AWT.f10", "F10");
			case VC_F11:
				return Toolkit.getProperty("AWT.f11", "F11");
			case VC_F12:
				return Toolkit.getProperty("AWT.f12", "F12");

			case VC_F13:
				return Toolkit.getProperty("AWT.f13", "F13");
			case VC_F14:
				return Toolkit.getProperty("AWT.f14", "F14");
			case VC_F15:
				return Toolkit.getProperty("AWT.f15", "F15");
			case VC_F16:
				return Toolkit.getProperty("AWT.f16", "F16");
			case VC_F17:
				return Toolkit.getProperty("AWT.f17", "F17");
			case VC_F18:
				return Toolkit.getProperty("AWT.f18", "F18");
			case VC_F19:
				return Toolkit.getProperty("AWT.f19", "F19");
			case VC_F20:
				return Toolkit.getProperty("AWT.f20", "F20");
			case VC_F21:
				return Toolkit.getProperty("AWT.f21", "F21");
			case VC_F22:
				return Toolkit.getProperty("AWT.f22", "F22");
			case VC_F23:
				return Toolkit.getProperty("AWT.f23", "F23");
			case VC_F24:
				return Toolkit.getProperty("AWT.f24", "F24");
			// End Function Keys

			// Begin Alphanumeric Zone
			case VC_BACKQUOTE:
				return Toolkit.getProperty("AWT.backQuote", "Back Quote");

			case VC_1:
				return "1";
			case VC_2:
				return "2";
			case VC_3:
				return "3";
			case VC_4:
				return "4";
			case VC_5:
				return "5";
			case VC_6:
				return "6";
			case VC_7:
				return "7";
			case VC_8:
				return "8";
			case VC_9:
				return "9";
			case VC_0:
				return "0";

			case VC_MINUS:
				return Toolkit.getProperty("AWT.minus", "Minus");
			case VC_EQUALS:
				return Toolkit.getProperty("AWT.equals", "Equals");
			case VC_BACKSPACE:
				return Toolkit.getProperty("AWT.backSpace", "Backspace");

			case VC_TAB:
				return Toolkit.getProperty("AWT.tab", "Tab");
			case VC_CAPS_LOCK:
				return Toolkit.getProperty("AWT.capsLock", "Caps Lock");

			case VC_A:
				return "A";
			case VC_B:
				return "B";
			case VC_C:
				return "C";
			case VC_D:
				return "D";
			case VC_E:
				return "E";
			case VC_F:
				return "F";
			case VC_G:
				return "G";
			case VC_H:
				return "H";
			case VC_I:
				return "I";
			case VC_J:
				return "J";
			case VC_K:
				return "K";
			case VC_L:
				return "L";
			case VC_M:
				return "M";
			case VC_N:
				return "N";
			case VC_O:
				return "O";
			case VC_P:
				return "P";
			case VC_Q:
				return "Q";
			case VC_R:
				return "R";
			case VC_S:
				return "S";
			case VC_T:
				return "T";
			case VC_U:
				return "U";
			case VC_V:
				return "V";
			case VC_W:
				return "W";
			case VC_X:
				return "X";
			case VC_Y:
				return "Y";
			case VC_Z:
				return "Z";

			case VC_OPEN_BRACKET:
				return Toolkit.getProperty("AWT.openBracket", "Open Bracket");
			case VC_CLOSE_BRACKET:
				return Toolkit.getProperty("AWT.closeBracket", "Close Bracket");
			case VC_BACK_SLASH:
				return Toolkit.getProperty("AWT.backSlash", "Back Slash");

			case VC_SEMICOLON:
				return Toolkit.getProperty("AWT.semicolon", "Semicolon");
			case VC_QUOTE:
				return Toolkit.getProperty("AWT.quote", "Quote");
			case VC_ENTER:
				return Toolkit.getProperty("AWT.enter", "Enter");

			case VC_COMMA:
				return Toolkit.getProperty("AWT.comma", "Comma");
			case VC_PERIOD:
				return Toolkit.getProperty("AWT.period", "Period");
			case VC_SLASH:
				return Toolkit.getProperty("AWT.slash", "Slash");

			case VC_SPACE:
				return Toolkit.getProperty("AWT.space", "Space");
			// End Alphanumeric Zone

			case VC_PRINTSCREEN:
				return Toolkit.getProperty("AWT.printScreen", "Print Screen");
			case VC_SCROLL_LOCK:
				return Toolkit.getProperty("AWT.scrollLock", "Scroll Lock");
			case VC_PAUSE:
				return Toolkit.getProperty("AWT.pause", "Pause");

			// Begin Edit Key Zone
			case VC_INSERT:
				return Toolkit.getProperty("AWT.insert", "Insert");
			case VC_DELETE:
				return Toolkit.getProperty("AWT.delete", "Delete");
			case VC_HOME:
				return Toolkit.getProperty("AWT.home", "Home");
			case VC_END:
				return Toolkit.getProperty("AWT.end", "End");
			case VC_PAGE_UP:
				return Toolkit.getProperty("AWT.pgup", "Page Up");
			case VC_PAGE_DOWN:
				return Toolkit.getProperty("AWT.pgdn", "Page Down");
			// End Edit Key Zone


			// Begin Cursor Key Zone
			case VC_UP:
				return Toolkit.getProperty("AWT.up", "Up");
			case VC_LEFT:
				return Toolkit.getProperty("AWT.left", "Left");
			case VC_CLEAR:
				return Toolkit.getProperty("AWT.clear", "Clear");
			case VC_RIGHT:
				return Toolkit.getProperty("AWT.right", "Right");
			case VC_DOWN:
				return Toolkit.getProperty("AWT.down", "Down");
			// End Cursor Key Zone


			// Begin Numeric Zone
			case VC_NUM_LOCK:
				return Toolkit.getProperty("AWT.numLock", "Num Lock");
			case VC_SEPARATOR:
				return Toolkit.getProperty("AWT.separator", "NumPad ,");
			// End Numeric Zone

			// Begin Modifier and Control Keys
			case VC_SHIFT:
				return	Toolkit.getProperty("AWT.shift", "Shift");
			case VC_CONTROL:
				return Toolkit.getProperty("AWT.control", "Control");
			case VC_ALT:
				return Toolkit.getProperty("AWT.alt", "Alt");
			case VC_META:
				return Toolkit.getProperty("AWT.meta", "Meta");
			case VC_CONTEXT_MENU:
				return Toolkit.getProperty("AWT.context", "Context Menu");
			// End Modifier and Control Keys


			// Begin Media Control Keys
			case VC_POWER:
				return Toolkit.getProperty("AWT.power", "Power");
			case VC_SLEEP:
				return Toolkit.getProperty("AWT.sleep", "Sleep");
			case VC_WAKE:
				return Toolkit.getProperty("AWT.wake", "Wake");

			case VC_MEDIA_PLAY:
				return Toolkit.getProperty("AWT.play", "Play");
			case VC_MEDIA_STOP:
				return Toolkit.getProperty("AWT.stop", "Stop");
			case VC_MEDIA_PREVIOUS:
				return Toolkit.getProperty("AWT.previous", "Previous");
			case VC_MEDIA_NEXT:
				return Toolkit.getProperty("AWT.next", "Next");
			case VC_MEDIA_SELECT:
				return Toolkit.getProperty("AWT.select", "Select");
			case VC_MEDIA_EJECT:
				return Toolkit.getProperty("AWT.eject", "Eject");

			case VC_VOLUME_MUTE:
				return Toolkit.getProperty("AWT.mute", "Mute");
			case VC_VOLUME_UP:
				return Toolkit.getProperty("AWT.volup", "Volume Up");
			case VC_VOLUME_DOWN:
				return Toolkit.getProperty("AWT.voldn", "Volume Down");

			case VC_APP_MAIL:
				return Toolkit.getProperty("AWT.app_mail", "App Mail");
			case VC_APP_CALCULATOR:
				return Toolkit.getProperty("AWT.app_calculator", "App Calculator");
			case VC_APP_MUSIC:
				return Toolkit.getProperty("AWT.app_music", "App Music");
			case VC_APP_PICTURES:
				return Toolkit.getProperty("AWT.app_pictures", "App Pictures");

			case VC_BROWSER_SEARCH:
				return Toolkit.getProperty("AWT.search", "Browser Search");
			case VC_BROWSER_HOME:
				return Toolkit.getProperty("AWT.homepage", "Browser Home");
			case VC_BROWSER_BACK:
				return Toolkit.getProperty("AWT.back", "Browser Back");
			case VC_BROWSER_FORWARD:
				return Toolkit.getProperty("AWT.forward", "Browser Forward");
			case VC_BROWSER_STOP:
				return Toolkit.getProperty("AWT.stop", "Browser Stop");
			case VC_BROWSER_REFRESH:
				return Toolkit.getProperty("AWT.refresh", "Browser Refresh");
			case VC_BROWSER_FAVORITES:
				return Toolkit.getProperty("AWT.favorites", "Browser Favorites");
			// End Media Control Keys

			// Begin Japanese Language Keys
			case VC_KATAKANA:
				return Toolkit.getProperty("AWT.katakana", "Katakana");
			case VC_UNDERSCORE:
				return Toolkit.getProperty("AWT.underscore", "Underscore");
			case VC_FURIGANA:
				return Toolkit.getProperty("AWT.furigana", "Furigana");
			case VC_KANJI:
				return Toolkit.getProperty("AWT.kanji", "Kanji");
			case VC_HIRAGANA:
				return Toolkit.getProperty("AWT.hiragana", "Hiragana");
			case VC_YEN:
				return Toolkit.getProperty("AWT.yen", Character.toString((char) 0x00A5));
			// End Japanese Language Keys


			// Begin Sun keyboards
			case VC_SUN_HELP:
				return Toolkit.getProperty("AWT.sun_help", "Sun Help");

			case VC_SUN_STOP:
				return Toolkit.getProperty("AWT.sun_stop", "Sun Stop");
			case VC_SUN_PROPS:
				return Toolkit.getProperty("AWT.sun_props", "Sun Props");
			case VC_SUN_FRONT:
				return Toolkit.getProperty("AWT.sun_front", "Sun Front");
			case VC_SUN_OPEN:
				return Toolkit.getProperty("AWT.sun_open", "Sun Open");
			case VC_SUN_FIND:
				return Toolkit.getProperty("AWT.sun_find", "Sun Find");
			case VC_SUN_AGAIN:
				return Toolkit.getProperty("AWT.sun_again", "Sun Again");
			case VC_SUN_COPY:
				return Toolkit.getProperty("AWT.sun_copy", "Sun Copy");
			case VC_SUN_INSERT:
				return Toolkit.getProperty("AWT.sun_insert", "Sun Insert");
			case VC_SUN_CUT:
				return Toolkit.getProperty("AWT.sun_cut", "Sun Cut");
			// End Sun keyboards

			case VC_UNDEFINED:
				return Toolkit.getProperty("AWT.undefined", "Undefined");
		}

		return Toolkit.getProperty("AWT.unknown", "Unknown") +
				" keyCode: 0x" + Integer.toString(keyCode, 16);
	}


	/**
	 * Returns whether the key in this event is an "action" key.  Typically, an
	 * action key does not fire a Unicode character and is not a modifier key.
	 *
	 * @return <code>true</code> if the key is an "action" key,
	 * <code>false</code> otherwise.
	 *
	 * @since 1.1
	 */
	public boolean isActionKey() {
		switch (keyCode) {
			// Function Keys
			case VC_F1:
			case VC_F2:
			case VC_F3:
			case VC_F4:
			case VC_F5:
			case VC_F6:
			case VC_F7:
			case VC_F8:
			case VC_F9:
			case VC_F10:
			case VC_F11:
			case VC_F12:

			case VC_F13:
			case VC_F14:
			case VC_F15:
			case VC_F16:
			case VC_F17:
			case VC_F18:
			case VC_F19:
			case VC_F20:
			case VC_F21:
			case VC_F22:
			case VC_F23:
			case VC_F24:

			// Alphanumeric Zone
			case VC_CAPS_LOCK:

			case VC_PRINTSCREEN:
			case VC_SCROLL_LOCK:

			// Edit Key Zone
			case VC_INSERT:
			case VC_HOME:
			case VC_END:
			case VC_PAGE_UP:
			case VC_PAGE_DOWN:

			// Cursor Key Zone
			case VC_UP:
			case VC_LEFT:
			case VC_CLEAR:
			case VC_RIGHT:
			case VC_DOWN:

			// Numeric Zone
			case VC_NUM_LOCK:

			// Modifier and Control Keys
			case VC_SHIFT:
			case VC_CONTROL:
			case VC_ALT:
			case VC_META:
			case VC_CONTEXT_MENU:

			// Media Control Keys
			case VC_POWER:
			case VC_SLEEP:
			case VC_WAKE:

			case VC_MEDIA_PLAY:
			case VC_MEDIA_STOP:
			case VC_MEDIA_PREVIOUS:
			case VC_MEDIA_NEXT:
			case VC_MEDIA_SELECT:
			case VC_MEDIA_EJECT:

			case VC_VOLUME_MUTE:
			case VC_VOLUME_UP:
			case VC_VOLUME_DOWN:

			case VC_APP_MAIL:
			case VC_APP_CALCULATOR:
			case VC_APP_MUSIC:
			case VC_APP_PICTURES:

			case VC_BROWSER_SEARCH:
			case VC_BROWSER_HOME:
			case VC_BROWSER_BACK:
			case VC_BROWSER_FORWARD:
			case VC_BROWSER_STOP:
			case VC_BROWSER_REFRESH:
			case VC_BROWSER_FAVORITES:

			// Japanese Language Keys
			case VC_KATAKANA:
			case VC_FURIGANA:
			case VC_KANJI:
			case VC_HIRAGANA:

			// Sun keyboards
			case VC_SUN_HELP:

			case VC_SUN_STOP:
			case VC_SUN_PROPS:
			case VC_SUN_FRONT:
			case VC_SUN_OPEN:
			case VC_SUN_FIND:
			case VC_SUN_AGAIN:
			case VC_SUN_UNDO:
			case VC_SUN_COPY:
			case VC_SUN_INSERT:
			case VC_SUN_CUT:

				return true;
		}

		return false;
	}


	/**
	 * Returns a parameter string identifying this event. This method is useful
	 * for event logging and debugging.
	 *
	 * @return a string identifying the event and its attributes.
	 */
	@Override
	public String paramString() {
		StringBuilder param = new StringBuilder(255);

		switch(getID()) {
			case NATIVE_KEY_PRESSED:
				param.append("NATIVE_KEY_PRESSED");
				break;

			case NATIVE_KEY_RELEASED:
				param.append("NATIVE_KEY_RELEASED");
				break;

			case NATIVE_KEY_TYPED:
				param.append("NATIVE_KEY_TYPED");
				break;

			default:
				param.append("unknown type");
				break;
		}
		param.append(',');

		param.append("keyCode=");
		param.append(keyCode);
		param.append(',');

		param.append("keyText=");
		param.append(getKeyText(keyCode));
		param.append(',');

		param.append("keyChar=");
		switch (keyChar) {
			case VC_BACKSPACE:
			case VC_DELETE:
			case VC_ESCAPE:
			case VC_ENTER:
			case VC_TAB:
				param.append(getKeyText(keyChar));
				break;
			case CHAR_UNDEFINED:
				param.append(getKeyText(VC_UNDEFINED));
				break;
			default:
				param.append('\'');
				param.append(keyChar);
				param.append('\'');
				break;

		}
		param.append(',');

		if (getModifiers() != 0) {
			param.append("modifiers=");
			param.append(getModifiersText(getModifiers()));
			param.append(',');
		}

		param.append("keyLocation=");
		switch (keyLocation) {
			case KEY_LOCATION_UNKNOWN:
				param.append("KEY_LOCATION_UNKNOWN");
				break;

			case KEY_LOCATION_STANDARD:
				param.append("KEY_LOCATION_STANDARD");
				break;

			case KEY_LOCATION_LEFT:
				param.append("KEY_LOCATION_LEFT");
				break;

			case KEY_LOCATION_RIGHT:
				param.append("KEY_LOCATION_RIGHT");
				break;

			case KEY_LOCATION_NUMPAD:
				param.append("KEY_LOCATION_NUMPAD");
				break;

			default:
				param.append("KEY_LOCATION_UNKNOWN");
				break;
			}
		param.append(',');

		param.append("rawCode=");
		param.append(rawCode);

		return param.toString();
	}
}
