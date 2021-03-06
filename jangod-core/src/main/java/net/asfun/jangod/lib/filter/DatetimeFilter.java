/**********************************************************************
Copyright (c) 2009 Asfun Net.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
**********************************************************************/
package net.asfun.jangod.lib.filter;

import static net.asfun.jangod.util.logging.JangodLogger;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import net.asfun.jangod.interpret.InterpretException;
import net.asfun.jangod.interpret.JangodInterpreter;
import net.asfun.jangod.lib.Filter;
import net.asfun.jangod.util.logging.Level;

public class DatetimeFilter implements Filter{

	@Override
	public Object filter(Object object, JangodInterpreter interpreter, String... arg) throws InterpretException {
		if ( object == null ) {
			return object;
		}
		SimpleDateFormat sdf;
		if ( arg.length == 1 ) {
			sdf = new SimpleDateFormat(interpreter.resolveString(arg[0]));
			sdf.setTimeZone(interpreter.getConfiguration().getTimezone());
		} else if ( arg.length == 2 ) {
			sdf = new SimpleDateFormat(interpreter.resolveString(arg[0]));
			sdf.setTimeZone(TimeZone.getTimeZone(interpreter.resolveString(arg[1])));
		} else {
			throw new InterpretException("filter date expects 1 or 2 args >>> " + arg.length);
		}
		try { 
			return sdf.format(object);
		} catch (Exception e) {
			JangodLogger.log(Level.SEVERE, "filter date can't format a datetime >>> " + object, e.getCause());
		}
		return object;
	}

	@Override
	public String getName() {
		return "date";
	}

}
