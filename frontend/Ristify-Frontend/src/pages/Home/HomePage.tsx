import "./HomePage.scss"
import Footer from "../../components/Footer/Footer.tsx";
import {Box} from "@chakra-ui/react";
import {useMediaController} from "../../hooks/CustomHooks.ts";

export default function HomePage() {
    const {mediaController, onBack, onSkip} = useMediaController();
    // const mockSong: Song = {
    //     songName: "Humble",
    //     artistName: "Kendrick Lamar",
    //     songId: 2,
    //     url: "",
    //     albumName: "StayStrongKid"
    // }
    //
    // const [songProgress, setSongProgress] = useState<number>(0);
    // const [playing, setPlaying] = useState<boolean>(false);
    //
    // useEffect(() => {
    //     if (playing) {
    //         const timeout = setTimeout(() => {
    //             setSongProgress(songProgress + 1);
    //         }, 1000)
    //         return () => {
    //             clearTimeout(timeout);
    //         }
    //     }
    // }, [songProgress, playing]);
    // const onSongSlide = (newProgress: number): void => {
    //     setSongProgress(newProgress);
    // }
    //
    // const onPlay = () => {
    //     setPlaying(true);
    // }
    // const onPause = () => {
    //     setPlaying(false)
    // }

    return (<>
        <Box w="100vw" h="100vh" display="flex" alignItems="flex-start" justifyContent="space-between" flexDirection="column">
            {/* main */}
            <Box w="full" h="full" bgColor="background.darker" display="flex" flexDirection="row" gap={2} p={2}>
                {/**/}
                <Box h="full" w="25%" bgColor="background.base" rounded="lg">
                </Box>

                <Box h="full" w="50%" bgColor="background.base" rounded="lg">
                </Box>

                <Box h="full" w="25%" bgColor="background.base" rounded="lg">
                </Box>
            </Box>
            {/**/}
            {/* play navigation */}
            <Footer mediaController={mediaController} onBack={onBack} onSkip={onSkip}/>
        </Box>
    </>)
}



