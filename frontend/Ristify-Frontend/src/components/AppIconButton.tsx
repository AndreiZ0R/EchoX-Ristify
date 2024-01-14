import {ReactElement} from "react";
import {IconButton} from "@chakra-ui/react";

type AppIconButtonProps = {
    ariaLabel: string,
    icon: ReactElement,
    scale?: number,
    onClick?: () => void,
    isActive?: boolean,
    initialScale?: number
}

export default function AppIconButton({ariaLabel, icon, scale, onClick, initialScale, isActive = false}: AppIconButtonProps) {
    return <>
        <IconButton
            bgColor="transparent"
            color={isActive ? "primary.lighter" : "background.light"}
            transform={initialScale ? `scale(${initialScale})` : "scale(1.3)"}
            _hover={{backgroundColor: "transparent", color: "primary.base", transform: scale ? `scale(${scale})` : "scale(1.5)"}}
            icon={icon}
            aria-label={ariaLabel}
            onClick={onClick}
        />
    </>
}