package ${package}.Initializers;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

import ${package}.Plugin;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class CommandInit {

	public static void init() {
		try {
			ClassPath cp = ClassPath.from(CommandInit.class.getClassLoader());
			for (ClassInfo classInfo : cp.getTopLevelClassesRecursive("${package}.Commands.Commands")) {
				Class<?> clazz = Class.forName(classInfo.getName());
				if (CommandExecutor.class.isAssignableFrom(clazz)) {
					CommandExecutor command = (CommandExecutor) clazz.getDeclaredConstructor().newInstance();
					String commandName = classInfo.getSimpleName().replace("Command", "");
					Plugin.plugin.getCommand(commandName).setExecutor(command);
				}
			}
			for (ClassInfo classInfo : cp.getTopLevelClassesRecursive("${package}.Commands.TabCompleters")) {
				Class<?> clazz = Class.forName(classInfo.getName());
				if (TabCompleter.class.isAssignableFrom(clazz)) {
					TabCompleter tabCompleter = (TabCompleter) clazz.getDeclaredConstructor().newInstance();
					String commandName = classInfo.getSimpleName().replace("TabCompleter", "");
					Plugin.plugin.getCommand(commandName).setTabCompleter(tabCompleter);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}