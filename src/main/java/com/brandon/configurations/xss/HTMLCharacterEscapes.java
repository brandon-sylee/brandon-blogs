package com.brandon.configurations.xss;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import org.apache.commons.lang3.text.translate.AggregateTranslator;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.apache.commons.lang3.text.translate.LookupTranslator;

/**
 * Created by brandon Lee on 2016-11-22.
 */
public class HTMLCharacterEscapes extends CharacterEscapes {
	private final int[] asciiEscapes;
	private final CharSequenceTranslator translator;

	public HTMLCharacterEscapes() {
		// 1. XSS 방지 처리할 특수 문자 지정
		asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
		asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['('] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes[')'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;

		// 2. XSS 방지 처리 특수 문자 인코딩 값 지정
		translator = new AggregateTranslator(
			new LookupTranslator(EntityArrays.BASIC_ESCAPE()), // <, >, &, " 는 여기에 포함됨
			new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE()),
			new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE()),
			// 여기에서 커스터마이징 가능
			new LookupTranslator(
				new String[][] {
					{"(", "&#40;"},
					{")", "&#41;"},
					{"#", "&#35;"},
					{"\'", "&#39;"}
				}));
	}

	@Override
	public int[] getEscapeCodesForAscii() {
		return asciiEscapes;
	}

	@Override
	public SerializableString getEscapeSequence(int ch) {
		return new SerializedString(translator.translate(Character.toString((char) ch)));
	}
}
