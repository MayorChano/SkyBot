/*
 * Skybot, a multipurpose discord bot
 *      Copyright (C) 2017 - 2019  Duncan "duncte123" Sterken & Ramid "ramidzkh" Khan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ml.duncte123.skybot;

import gnu.trove.list.TLongList;
import gnu.trove.list.array.TLongArrayList;

import static java.lang.System.getProperty;

@Author(nickname = "duncte123", author = "Duncan Sterken")
public class Settings {

    public static String PREFIX = "db!";
    public static final long OWNER_ID = 191231307290771456L;
    public static final TLongList developers = new TLongArrayList();
    public static final String OTHER_PREFIX = "db.";
    public static final String VERSION = "@versionObj@";
    public static final String KOTLIN_VERSION = "@kotlinVersion@";
    public static final String DEFAULT_NAME = "DuncteBot";
    public static final String DEFAULT_ICON = "https://bot.duncte123.me/img/favicon.png";
    public static final int defaultColour = 0x0751c6;
    public static final boolean enableUpdaterCommand = getProperty("updater") != null;
    public static final boolean useJSON = false;

}
