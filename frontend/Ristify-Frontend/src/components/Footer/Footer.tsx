import {ChevronLeftIcon, ChevronRightIcon} from "@chakra-ui/icons";
import {Box, Flex, Hide, IconButton, Image, Slider, SliderFilledTrack, SliderThumb, SliderTrack, Text} from "@chakra-ui/react";
import {MouseEventHandler, ReactElement} from "react";
import {FaPause, FaPlay} from "react-icons/fa";
import {FaCircle, FaVolumeLow} from "react-icons/fa6";
import {MediaController} from "../../models/Playlist.ts";
import {MdGraphicEq} from "react-icons/md";


type FooterProps = {
    mediaController: MediaController
    onBack: () => void,
    onSkip: () => void,
    // song: Song,
    // progress: number,
    // songDuration: number,
    // onSliderChange: (value: number) => void,
    // playing: boolean,
    // onPause: () => void,
    // onPlay: () => void
}
const Footer = ({mediaController, onBack, onSkip}: FooterProps) => {
    return (
        <Flex
            bg='background.darker'
            w="full"
            p={[8, 4]}
            justifyContent="space-between"
            alignItems="center"
            direction="row"
        >
            <Box display="flex" alignItems="center" gap={3} maxW="35%" minW="17%">
                <Image
                    w="75px"
                    h="75px"
                    fit="cover"
                    borderRadius="12px"
                    src={mediaController.currentSong.imageUrl}
                    alt="TemplatesKart"
                />
                <Box width="150px">
                    <Text color="background.light" isTruncated>{mediaController.currentSong.songName}</Text>
                    <Text color="gray" isTruncated>{mediaController.currentSong.artistName}</Text>
                </Box>
            </Box>


            <Box alignItems="center" display="flex" minW="60%" h="full" flexDir="column">
                <Box gap={15}>
                    <FooterButton label="back" icon={<ChevronLeftIcon/>} onClick={onBack}/>
                    <FooterButton label="play" icon={mediaController.playing ? <FaPause/> : <FaPlay/>}
                                  onClick={mediaController.playing ? mediaController.onPause : mediaController.onPlay}/>
                    <FooterButton label="forward" icon={<ChevronRightIcon/>} onClick={onSkip}/>
                </Box>
                <Box w="full">
                    <Slider
                        aria-label='slider-ex-4'
                        defaultValue={0}
                        value={mediaController.songProgress}
                        focusThumbOnChange={false}
                        max={mediaController.currentSong.duration}
                        onChange={mediaController.onSliderChange}
                    >
                        <SliderTrack bg='gray'>
                            <SliderFilledTrack bg='primary.base'/>
                        </SliderTrack>

                        <SliderThumb boxSize={6} bgColor="transparent">
                            <Box color='primary.base' as={FaCircle}/>
                        </SliderThumb>
                    </Slider>
                </Box>
            </Box>

            <Hide below="md">
                <Box maxW="250px" minW="15%" flexDirection="row" alignItems="center" justifyContent="center" flexDir="row" display="flex">
                    <FooterButton label="volume" icon={<FaVolumeLow/>} scale={1.6}/>
                    <Slider aria-label='slider-ex-4' defaultValue={30}>
                        <SliderTrack bg='gray'>
                            <SliderFilledTrack bg='primary.base'/>
                        </SliderTrack>
                        <SliderThumb boxSize={6}>
                            <Box color='primary.base' as={MdGraphicEq}/>
                        </SliderThumb>
                    </Slider>
                </Box>
            </Hide>
        </Flex>
    );
};

type NavBtnProps = {
    label: string,
    icon: ReactElement | undefined,
    scale?: number,
    onClick?: MouseEventHandler<HTMLButtonElement>
}

function FooterButton({label, icon, scale, onClick}: NavBtnProps) {
    return <>
        <IconButton
            bgColor="transparent"
            color="background.light"
            transform="scale(1.5)"
            _hover={{backgroundColor: "transparent", color: "background.light", transform: scale ? `scale(${scale})` : "scale(1.75)"}}
            icon={icon}
            aria-label={label}
            onClick={onClick}
        />
    </>
}

export default Footer;