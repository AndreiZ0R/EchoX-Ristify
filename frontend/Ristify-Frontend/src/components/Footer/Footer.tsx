import {ChevronLeftIcon, ChevronRightIcon} from "@chakra-ui/icons";
import {Box, Flex, Hide, Image, Slider, SliderFilledTrack, SliderThumb, SliderTrack, Text} from "@chakra-ui/react";
import {useState} from "react";
import {FaPause, FaPlay} from "react-icons/fa";
import {FaCircle, FaVolumeLow} from "react-icons/fa6";
import {SlLoop} from "react-icons/sl";
import {IoShuffleOutline} from "react-icons/io5";
import {MediaController} from "../../models/Playlist.ts";
import {MdGraphicEq} from "react-icons/md";
import AppIconButton from "../AppIconButton.tsx";

type FooterProps = {
    mediaController: MediaController
    onBack: () => void,
    onSkip: () => void,
}
const Footer = ({mediaController, onBack, onSkip}: FooterProps) => {
    const [shuffle, setShuffle] = useState<boolean>(false);
    const [loop, setLoop] = useState<boolean>(false);

    return (
        <Flex
            bg='background.darker'
            w="full"
            p={[8, 3]}
            justifyContent="space-between"
            alignItems={{base: "center"}}
            direction={{base: "column", sm: "column", md: "row", lg: "row"}}
        >
            <audio src={mediaController.currentSong.url} ref={mediaController.audioRef}></audio>

            <Box display="flex" alignItems="center" justifyContent="center" gap={3} maxW={{base: "full", md: "35%"}} minW={{base: "full", md: "17%"}}>
                <Image
                    w="75px"
                    h="75px"
                    fit="cover"
                    borderRadius="12px"
                    src={mediaController.currentSong.imageUrl}
                    alt="TemplatesKart"
                />
                <Box width={{base: "full", sm: "full", md: "150px"}}>
                    <Text color="background.light" isTruncated>{mediaController.currentSong.songName}</Text>
                    <Text color="gray" isTruncated>{mediaController.currentSong.artistName}</Text>
                </Box>
            </Box>

            <Box alignItems="center" display="flex" minW={{base: "full", sm: "full%", md: "50%", lg: "60%"}} h="full" flexDir="column" justifyContent="center">
                <Box gap={15} display="flex" alignItems="center" justifyContent="space-between">
                    <AppIconButton ariaLabel="shuffle" icon={<IoShuffleOutline/>} isActive={shuffle} onClick={() => setShuffle(!shuffle)}/>
                    <AppIconButton ariaLabel="back" icon={<ChevronLeftIcon/>} onClick={onBack}/>
                    <AppIconButton ariaLabel="play" icon={mediaController.playing ? <FaPause/> : <FaPlay/>}
                                   onClick={mediaController.playing ? mediaController.onPause : mediaController.onPlay}/>
                    <AppIconButton ariaLabel="forward" icon={<ChevronRightIcon/>} onClick={onSkip}/>
                    <AppIconButton ariaLabel="loop" icon={<SlLoop/>} isActive={loop} onClick={() => setLoop(!loop)}/>
                </Box>
                <Box w="full" display="flex" flexDirection="row" gap={2}>
                    <Text color="gray" cursor="default">{new Date(mediaController.songProgress * 1000).toISOString().slice(14, 19)}</Text>
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
                    <Text color="gray" cursor="default">{new Date(mediaController.currentSong.duration * 1000).toISOString().slice(14, 19)}</Text>
                </Box>
            </Box>

            {/*volume*/}
            <Hide below="md">
                <Box maxW="250px" minW="15%" flexDirection="row" alignItems="center" justifyContent="center" flexDir="row" display="flex" mr={2}>
                    <AppIconButton ariaLabel="volume" icon={<FaVolumeLow/>} scale={1.6}/>
                    <Slider
                        aria-label='slider-ex-4'
                        defaultValue={mediaController.volume}
                        value={mediaController.volume}
                        onChange={mediaController.onVolumeChange}
                    >
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

export default Footer;